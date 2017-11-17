(declare base-uri 'file:///d:/Notlar-2/kodlar/Scrapper/data/xmldocs/';
                for $tel in fn:doc('1.xml')//*[contains(@class,'model-listing-container-80')]/div
                return $tel