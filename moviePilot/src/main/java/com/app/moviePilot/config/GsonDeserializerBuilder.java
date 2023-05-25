package com.app.moviePilot.config;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
/**
 * 
 * @author Marino Burillo
 *
 */
public abstract class GsonDeserializerBuilder {
	public static Gson getDeserializedGson() {
		return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
		    @Override
		    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		        Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
		        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		    }		  
		  
		}).registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
		    @Override
		    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		        Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
		        return LocalDate.ofInstant(instant, ZoneId.systemDefault());
		    }		  
		  
		}).create();
	}
}
