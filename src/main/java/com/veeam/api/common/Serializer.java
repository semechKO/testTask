package com.veeam.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Serializer {

    private static ObjectMapper mapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

    /**
     * Object mapping into JSON
     *
     * @param object    - object, that will be mapped
     * @param inclusion - json inclusion
     * @return - JSON-string
     */
    public static String getJson(Object object, JsonInclude.Include inclusion) {
        String json = null;
        try {
            mapper.setSerializationInclusion(inclusion);
            json = mapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return json;
    }

    /**
     * Object mapping into XML
     *
     * @param object    - object, that will be mapped
     * @param inclusion - xml inclusion
     * @return - XML-string
     */
    public static String getXML(Object object, JsonInclude.Include inclusion) {
        String xml = null;
        try {
            xmlMapper.setSerializationInclusion(inclusion);
            xml = xmlMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return xml;
    }

    /**
     * Returns formatted json-string
     * Too make logs more good looking
     * If something goes wrong, returns sting without changes
     *
     * @param value - json-string
     * @return - formatted json-string
     */
    public static String formatJson(String value) {
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            Object json = mapper.readValue(value, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException e) {
            log.debug("Unable to format json");
            log.debug(e.getMessage());
        }
        return value;
    }

    /**
     * Returns formatted xml-string
     * To make logs more good looking
     * If something goes wrong, returns sting without changes
     *
     * @param value - json-string
     * @return - formatted json-string
     */
    public static String formatXml(String value) {
        try {
            xmlMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            Object xml = xmlMapper.readValue(value, Object.class);
            return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(xml);
        } catch (IOException e) {
            log.debug("Unable to format xml");
            log.debug(e.getMessage());
        }
        return value;
    }

    /**
     * @param xml
     * @param clazz
     * @return
     */
    public static Object parseXml(String xml, Class clazz) {
        try {
            return xmlMapper.readValue(xml, clazz);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse xml \"" + xml + "\"");
        }
        return null;
    }

    /**
     * Mapping json string to java class
     *
     * @param json  - json string
     * @param clazz - mapping class
     * @return - mapped object
     */
    public static Object parseJson(String json, Class clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse json \"" + json + "\"\n" + e.getMessage());
        }
        return null;
    }
}
