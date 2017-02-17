import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Test {

	public static void main(String[] args) throws Exception {

		File f = new File(args[0]);
		System.out.println("Free space: "+f.getFreeSpace()/1024);
		System.out.println("Usable space: "+f.getUsableSpace()/(1024*1024*1024));
		System.out.println("Total space: "+f.getTotalSpace()/1024);
		System.out.println(Files.getFileStore(Paths.get(args[0]).toRealPath()).getUsableSpace());
		
		System.out.println("oracle.apps.setup.topology.runtime.mbean:Location=FunctionalSetupServer,name=TopologyInfo,type=TopologyInfoRuntimeMBean,Application=SetupApp,ApplicationVersion=V2.0".replace("FunctionalSetupServer", "test"));
	}

}
