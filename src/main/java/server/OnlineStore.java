package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnlineStore {

    private final Server server = new Server(8080);
    private final Logger logger = LoggerFactory.getLogger(OnlineStore.class);

    public OnlineStore() throws Exception {
        startServer();
    }


    public  void startServer() throws Exception {
        //Instantiate a WebAbbContext to set a path to where to work from.
        var webApp = new WebAppContext(
                Resource.newClassPathResource("/webapp"),
                "/"
        );

        //Adding Servlet - To handle the actual page.
        var addItemServlet = new ServletHolder(new AddItemServlet());
        webApp.addServlet(addItemServlet, "/api/addItem");

        var listItemServlet = new ServletHolder(new ListItemServlet());
        webApp.addServlet(listItemServlet, "/api/listItems");

        server.setHandler(webApp);

        server.start();
        logger.info("Server started on {}", server.getURI());
    }

    public static void main(String[] args) throws Exception {
        var server = new OnlineStore();
    }

}
