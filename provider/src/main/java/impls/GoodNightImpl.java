package impls;

import annotation.Expose;
import interfaces.GoodNight;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
@Expose
public class GoodNightImpl implements GoodNight {
	@Override
	public String sayGoodNight(String word) {
		return "Good night,  " + word;
	}
}
