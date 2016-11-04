package some.name.idk.shutdownhook;

/**
 * Hello world!
 *
 */
public class App 
{
    private static volatile boolean shutDownSignalReceived = false;

    public static void main( String[] args ) {	
    	App app = new App();
        System.out.println( "Hello World!" );
        final Thread mainThread = Thread.currentThread();
        
        Thread shutdownHook = new Thread("shutdownHook"){
        	@Override
        	public void run(){
        		System.out.println( "shutdownHook run()" );
        		shutDownSignalReceived = true;
        		
				try {
					mainThread.interrupt();
					mainThread.join();
				} catch (InterruptedException e) {
					System.out.println( "Interrupted" );
				}
				
        		System.out.println( "End of shtudown hook" );
        	}
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    	shutDownSignalReceived=false;

		app.execute();
	
        System.out.println( "End of main()" );
    }
    
    public App(){

    }
    
    public void execute(){
    	
    	while(!shutDownSignalReceived){
    		System.out.println("I am sleep");
    		
    		try {
				Thread.sleep(1*1000);
			} catch (InterruptedException e) {
				System.out.println("execute() interrupted");
			}
    		System.out.println("I am not sleep");

    	}
    	System.out.println("end of execute()");
    }
}
