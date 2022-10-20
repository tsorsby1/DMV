import java.util.concurrent.Semaphore;
import java.lang.Thread;

class DMV{

 
    
    
    class Customer implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            
        }

    }

    class info_Desk implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            
        }

    }

    class Announcer implements Runnable{

        @Override
        public void run(){
            // TODO Auto-generated method stub
        }

    }

    class Agent implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            
        }
    }

    public static void main(String args[]){
        
        //MATERIAL FOR CUSTOMER THREAD
        int i=0;
        final int CUST_THREADS = 20;

        //MATERIAL FOR AGENT THREAD
        int j=0;
        final int AGENT_THREADS = 2;

         //MATERIAL FOR ANNOUNCER THREAD
         int k=0;
         final int ANNOUNCER_THREAD = 1;

          //MATERIAL FOR INFO DESK THREAD
          int l=0;
          final int INFO_DESK_THREAD = 1;

  
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
           myThread[i] = new Thread( thr[i] );
           myThread[i].start();
        }


        // create multiple Agent threads
        for(j = 0; j < AGENT_THREADS; ++j) 
        {
        thr2[j] = new Agent(j);
        myThread2[j] = new Thread( thr2[j] );
        myThread2[i].setDaemon(true);
        myThread2[j].start();
        }
        System.out.println("Hello World");

    }



}