package com.app.moviePilot.controller.filter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.JsonElement;
/**
 * 
 * @author Marino Burillo
 *
 */
public class FilterProcessor {
	private static final String URL ="https://api.themoviedb.org/3/";
	private static final String KEY = "api_key=6cacd119a397de0ec8845d760efdb7ab"; 
	@SuppressWarnings("serial")
	private static final Map<String, String> filterFactory = new HashMap<String, String>() {
		{
		put("filterMovie","discover/movie" );
		put("filterTv","discover/tv");
		put("searchMovie","search/movie");
		put("searchTv", "search/tv");
		put("movieById", "movie/%s");
		put("tvById", "tv/%s");
		}
	};
    public JsonElement processClientFilters(final Map<String, String> filters) {
    	UrlBuilder urlBuilder = UrlBuilder.create(URL, KEY);
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return urlBuilder.build();
    }
    //the action requested has to be one of the considered filters in the map, if it is not implemented it returns null
    public JsonElement postFilters(final String action, final Map<String,String> filters) {
    	Map<String,String> typeAndFilters = new LinkedHashMap<>();
    	if(filterFactory.containsKey(action)) {
            String endpoint = filterFactory.get(action);
            if (endpoint.contains("%s")) {
            	if(filters==null  || filters.get("id")==null) return null;
                String id = filters.get("id");
                endpoint = String.format(endpoint, id);
            }
            typeAndFilters.put("type", endpoint);
        } else {
            return null;
        }
    	if(filters!=null) typeAndFilters.putAll(filters);
    	typeAndFilters.remove("id");
    	return processClientFilters(typeAndFilters);   	
    }
}
