import java.util.concurrent.Semaphore;
import java.lang.Thread;
import java.util.LinkedList;
import java.util.Queue;

class DMV{

    private static Semaphore info_num = new Semaphore(1, true);
    
    
    private static Semaphore cust_id = new Semaphore(1, true);
    private static Semaphore cust_info_id_rdy = new Semaphore(1, true);
    private static Semaphore info_id = new Semaphore(1, true);
    private static Semaphore agent_id = new Semaphore(1, true);
    private static Semaphore mutex1 = new Semaphore(1, true);
   
    private static Semaphore cust_ready1 = new Semaphore(0, true);
    private static Semaphore cust_ready2 = new Semaphore(0, true);
    private static Semaphore cust_ready3 = new Semaphore(0, true);
    private static Semaphore cust_ready4 = new Semaphore(0, true);
    private static Semaphore id_ready = new Semaphore(1, true);
    private static Semaphore info_ln = new Semaphore(1, true);
    private static Semaphore info_box = new Semaphore(1, true);
    private static Semaphore agent_ln = new Semaphore(1, true);
    private static Semaphore wait_area = new Semaphore(1, true);
    private static Semaphore photo_exam = new Semaphore(1, true);
  

    



//Step1: Customers enter the DMV and wait to get to the info desk
//Step2: Once you reach the info desk they will tell you to go to the waiting room
      //and assign you a number
//Step3: The customer will enter the waiting room     


    
//signal = .release();
//wait = .acquire();
    



public static Queue<Integer> q1 = new LinkedList<>();


static int []cust_arr = new int[20];
static int info_num_cnt;

    static class Customer implements Runnable{
       
        private int cust_id;
        private int info_num;
        Customer(int cust_id){
            //Creating a customer_id
            this.cust_id = cust_id;            
        }
        @Override
        public void run() {
            
           System.out.println("Customer" + " " + cust_id + " enters DMV");
          
           //Customer waits for info_id
           try
           {
              info_id.acquire();
              mutex1.acquire();
              //dequeue the info_num
              info_num_cnt = q1.remove();
              System.out.println();
              System.out.println("Customer " + cust_id + " has number " + info_num_cnt);
              mutex1.release();
            }
           catch (InterruptedException e)
            {}
 
          

        }
    
    }
    
    
    
    static class info_Desk implements Runnable{

        @Override
        public void run() {
            System.out.println("Info Desk created");
            while(true){              
                //cust_id is the the cust identifier
                //info_num is the number we assign to the customer
                try {
                   info_box.acquire(); //waiting to get to info_box
                   mutex1.acquire();
                  
                   q1.add(info_num_cnt);
                   info_num_cnt++;
                   info_id.release(); //signaling the info id to the customer
                   mutex1.release();
                   

                   //info desk signaling they gave the customer an info id 
                    info_box.release(); //signaling for info id
                 } 
                 catch (InterruptedException e) 
                 {}                 
                
            }
            
        }


    }

    static class Announcer implements Runnable{
 
        @Override
        public void run(){
            System.out.println("Announcer created");
    
            while(true){
            
            }
         
        }

    }

    static class Agent implements Runnable{
        
            private int agent_id;

            Agent(int agent_id){
                this.agent_id = agent_id;
            }

        @Override
        public void run() {
            System.out.println("Agent" + " " + agent_id + " created");
         
            while(true){
                
            }
            
            
        }
    }

    public static void main(String args[]){
        
        //MATERIAL FOR CUSTOMER THREAD
        int i=0;
        final int CUST_THREADS = 20;

        //MATERIAL FOR AGENT THREAD
        int j=0;
        final int AGENT_THREADS = 2;

  
        //INSTANTIATING CUSTOMER THREAD
        Customer thr[] = new Customer[CUST_THREADS];
        Thread myThread[] = new Thread[CUST_THREADS];

        
        //INSTANTIATING AGENT THREAD
        Agent thr2[] = new Agent[AGENT_THREADS];
        Thread myThread2[] = new Thread[AGENT_THREADS];

        //INSTANTIATING ANOUNCER THREAD
        Announcer thr3 = new Announcer();
        Thread myThread3 = new Thread(thr3);
        myThread3.setDaemon(true);
        myThread3.start();

        //INSTANTIATING INFO_DESK THREAD
        info_Desk thr4 = new info_Desk();
        Thread myThread4 = new Thread(thr4);
        myThread4 = new Thread(thr4);
        myThread4.setDaemon(true);
        myThread4.start();

  
        
        
        // create multiple Customer threads
        for(i = 0; i < CUST_THREADS; ++i) 
        {
           thr[i] = new Customer(i);
           myThread[i]= new Thread(thr[i]);
           myThread[i].start();
        }


        // create multiple Agent threads
        for(j = 0; j < AGENT_THREADS; ++j) 
        {
        thr2[j] = new Agent(j);
        myThread2[j] = new Thread( thr2[j] );
        myThread2[j].setDaemon(true);
        myThread2[j].start();
        }

        
        //JOINING THE CUSTOMER THREADS
        for(i = 0; i < CUST_THREADS; ++i) 
      {
	    try
	    {
	    myThread[i].join();
	    }
	 catch (InterruptedException e)
	    {}
      }

    }
    }