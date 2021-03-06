/// package's name
package de.syntaktischer_zucker.diffusion;

/// imports
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * @brief base image processor
 * Note: Employ a strategy pattern for the pre_process and 
 * post_process methods iff this flexibility is necessary
 * @author stephanmg <stephan@syntaktischer-zucker.de>
 */
@Log4j2
@Setter
public class ImageProcessor {
	/// members
	private final FilterContext context = new FilterContext();
	private final Filter filter = new BaseFilter();
	private Image image;
	

	/// methods
	/**
	 * @brief
	 * @param url
	 */
	public void process(final URL url) {
		/// load image
		loadImage(url);
		
		/// pre process image
		preProcess();
		
		/// filter image
		context.setFilter(this.filter);
		context.doFilter(this.image);
		
		/// post process image
		postProcess();
		
	}
	
	/**
	 * @brief
	 */
	protected void preProcess() {
		
	}
	
	/**
	 * @brief
	 */
	protected void postProcess() {
		
	}
	
	/**
	 * @brief
	 * @param url 
	 */
	protected void loadImage(final URL url) {
		try {
			this.image = ImageIO.read(url);
		} catch (IOException ex) {
			log.error("Error reading image from file in path: ");
			log.error(url);
			log.error(ex);
		}
	}
}