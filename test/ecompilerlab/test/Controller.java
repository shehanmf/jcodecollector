package ecompilerlab.test;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/3/13
 * Time: 12:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);


                /*
                 * Instantiate the controller for this crawl.
                 */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

                /*
                 * For each crawl, you need to add some seed urls. These are the first
                 * URLs that are fetched and then the crawler starts following links
                 * which are found in these pages
                 */
//        controller.addSeed("http://www.java2s.com/Code/Java/CatalogJava.htm");
//        controller.addSeed("http://www.java2s.com/Code/Java/JDK-7/CatalogJDK-7.htm");
        controller.addSeed("http://www.java2s.com/Code/Java/EJB3/CatalogEJB3.htm");

                /*
                 * Start the crawl. This is a blocking operation, meaning that your code
                 * will reach the line after this only when crawling is finished.
                 */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}
