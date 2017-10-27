package itcs4180.homework5;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Aileen on 10/26/2017.
 */

public class PodcastParser {

    public static class PodcastsPullParser {
        static public ArrayList<Podcast> parsePodcasts(InputStream inputStream) throws XmlPullParserException, IOException {
            ArrayList<Podcast> podcasts = new ArrayList<>();
            Podcast podcast = new Podcast();


            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            // Associate parser with data
            parser.setInput(inputStream, "UTF-8");

            // Parser enables us to get events
            int event = parser.getEventType();

            // Loop through whole document
            while(event != XmlPullParser.END_DOCUMENT){

                switch(event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("entry")){
                            podcast = new Podcast();
                        }
                        else if(parser.getName().equals("updated")){
                            podcast.updatedDate = parser.nextText().trim();
                        }
                        else if(parser.getName().equals("im:releaseDate")){
                            podcast.releaseDate = String.valueOf(parser.getAttributeValue(null, "label"));
                        }
                        else if(parser.getName().equals("title")){
                            podcast.title = parser.nextText().trim();
                        }
                        else if(parser.getName().equals("summary")){
                            podcast.summary = parser.nextText().trim();
                        }
                        else if(parser.getName().equals("im:image")){
                            String attribute = String.valueOf(parser.getAttributeValue(null, "height"));
                            if(attribute.equals("55")){
                                // Smallest image
                                podcast.imageURlsmall = parser.nextText().trim();
                            }
                            else if(attribute.equals("170")){
                                // Largest image
                                podcast.imageURLlarge = parser.nextText().trim();
                            }
                            // For testing if there were any without either sizes 55, 60, and 170
                            // No "uh oh"s printed, so I guess it's safe :')?????
                            /*
                            else if(!attribute.equals("60")){
                                Log.d("Test-Parser", "uh oh");
                            }
                            */
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("entry")){
                            podcasts.add(podcast);
                        }
                        break;
                    default:
                        break;
                }

                // Get next event
                event = parser.next();
            } // end loop

            return podcasts;
        }
    }

}
