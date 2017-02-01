package com.drakeet.rebase.api.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * @author drakeet
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class GsonBodyProvider implements MessageBodyWriter<Object>,
    MessageBodyReader<Object> {

    private static final String UTF_8 = "UTF-8";


    @Override public boolean isReadable(
        Class<?> type, Type genericType,
        java.lang.annotation.Annotation[] annotations, MediaType mediaType) {
        return true;
    }


    @Override public Object readFrom(
        Class<Object> type, Type genericType,
        Annotation[] annotations, MediaType mediaType,
        MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {

        InputStreamReader streamReader = null;
        try {
            streamReader = new InputStreamReader(entityStream, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }

            return Globals.newGson().fromJson(streamReader, jsonType);
        } finally {
            try {
                streamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override public boolean isWriteable(
        Class<?> type, Type genericType,
        Annotation[] annotations, MediaType mediaType) {
        return true;
    }


    @Override public long getSize(
        Object object, Class<?> type, Type genericType,
        Annotation[] annotations, MediaType mediaType) {
        return -1;
    }


    @Override public void writeTo(
        Object object, Class<?> type, Type genericType,
        Annotation[] annotations, MediaType mediaType,
        MultivaluedMap<String, Object> httpHeaders,
        OutputStream entityStream) throws IOException, WebApplicationException {

        try (OutputStreamWriter writer = new OutputStreamWriter(entityStream, UTF_8)) {
            Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            Globals.newGson().toJson(object, jsonType, writer);
        }
    }
}