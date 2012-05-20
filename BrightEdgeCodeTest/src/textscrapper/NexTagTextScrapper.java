/**
 * 
 */
package textscrapper;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Stack;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * @author Rahul Jain - rjain2908 AT gmail DOT com
 *
 */
public class NexTagTextScrapper implements NexTagTextScrapperInterface {

	private static final String url_ = "http://www.nextag.com/jeans/products-html";
	/* (non-Javadoc)
	 * @see textscrapper.NexTagTextScrapperInterface#NumResults(java.lang.String)
	 */
	@Override
	public Integer NumResults(String keyword) throws Exception {
		if(keyword == null) {
			System.out.println("Keyword cannot be null.");
			return null;
		}
		Integer numResults = 0;
		URL url = new URL(url_);
		HTMLEditorKit kit = new HTMLEditorKit();
		HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
		doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
		Reader HTMLReader = new InputStreamReader(url.openConnection().getInputStream()); 
		kit.read(HTMLReader, doc, 0); 

		//  Get an iterator for all HTML tags.
		ElementIterator it = new ElementIterator(doc); 
		Element elem; 

		while( (elem = it.next()) != null  )
		{ 
			if((elem.getName().equals("meta")) && 
					(elem.getAttributes().getAttribute(HTML.Attribute.NAME)!=null) && 
					(elem.getAttributes().getAttribute(HTML.Attribute.NAME).equals("DESCRIPTION")))
			{
				String s = (String) elem.getAttributes().getAttribute(HTML.Attribute.CONTENT);
				System.out.println(s);
				int keyLen = keyword.length();
				if(s.length() <= keyLen*2+2)
				{
					return 0;
				}
				int i = keyLen+4;
				while(s.charAt(i)!=' ')
				{
					if(s.charAt(i)==',');
					else
					{
						numResults = (numResults*10) + (s.charAt(i)-'0');
					}
					i++;
				}
				return numResults;
			} 
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see textscrapper.NexTagTextScrapperInterface#GetResultInfo(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Result GetResultInfo(String keyword, Integer pageNumber) throws Exception {
		if(keyword == null || pageNumber == null || pageNumber < 1) {
			System.out.println("Invalid keyword or pageNumber.");
			return null;
		}
		//Result result = new Result();
		URL url = new URL(url_);
		HTMLEditorKit kit = new HTMLEditorKit();
		HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
		doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
		Reader HTMLReader = new InputStreamReader(url.openConnection().getInputStream()); 
		kit.read(HTMLReader, doc, 0); 

		//  Get an iterator for all HTML tags.
		ElementIterator it = new ElementIterator(doc); 
		Element elem;
		int num = 0;
		while( (elem = it.next()) != null  )
		{ 
			if((elem.getName().equals("div")) && 
					(elem.getAttributes().getAttribute(HTML.Attribute.CLASS)!=null) &&
					(elem.getAttributes().getAttribute(HTML.Attribute.CLASS).equals("sr-info")))
			{
				num++;
				//String s = (String) elem.getAttributes().getAttribute(HTML.Attribute.CLASS);
				//System.out.println(s);
				System.out.println(elem.getName());
				System.out.println(elem.toString());
				while(elem.getName()!="p-implied")
				{
					elem = it.next();
				}
				
				System.out.println(elem.toString());
				AttributeSet attributes = elem.getAttributes();
				System.out.println(attributes.getAttribute(HTML.Attribute.CLASS));
			}
			//System.out.println("num = "+num);
		}
		return null;
	}
}
