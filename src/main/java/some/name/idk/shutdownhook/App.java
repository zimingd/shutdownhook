package some.name.idk.shutdownhook;
/**
 * Hello world!
 *
 */
public class App 
{
    private ShutdownHook shutdownHook;

    public static void main( String[] args ) {	
    	App app = new App();
        System.out.println( "Hello World!" );
        final Thread mainThread = Thread.currentThread();

		app.execute();
	
        System.out.println( "End of main()" );
    }
    
    public App(){
        this.shutdownHook = new ShutdownHook(Thread.currentThread());
    }
    
    public void execute(){
    	
    	while(!shutdownHook.shouldShutDown()){
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