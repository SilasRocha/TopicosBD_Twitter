package analyzer;

import com.rmtheis.yandtran.ApiKeys;
import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;

public class Translation {

	public static String translate(String text, String language) {		
		Translate.setKey(ApiKeys.YANDEX_API_KEY);
		String translation = null;	
		
		try {		
			if (language.compareTo("PT") == 0)
				translation = Translate.execute(text, Language.PORTUGUESE, Language.ENGLISH);			

			if (language.compareTo("ESP") == 0)
				translation = Translate.execute(text, Language.SPANISH, Language.ENGLISH);			
			
			if (language.compareTo("ENG") == 0)
				translation = text;	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return translation;
	}

}