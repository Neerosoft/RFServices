package rf.services.com;
import javax.ws.rs.*;

@Path("/")
public class Services {

	public static int WINS, LOSSES, TIES;
	
	@GET
	@Path("/test")
	@Produces("application/json")
	public String getScore() {	          
	                String pattern = "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
	                return String.format(pattern,WINS,LOSSES,TIES);


	}

}
