package com.epam.mentoring.outtamemory.stackoverflow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Yun on 16.01.2016.
 */
public class JsonTranformRunner {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Shipper parent = createShipper(null, null);
        List<Shipper> children = new ArrayList<>();
        children.add(createShipper(parent, null));
        children.add(createShipper(parent, null));
        children.add(createShipper(parent, null));
        children.add(createShipper(parent, null));
        children.add(createShipper(parent, null));
        children.add(createShipper(parent, null));
        parent.setChildren(children);
        String json = objectMapper.writeValueAsString(parent);
    }

    private static Shipper createShipper(Shipper parent, List<Shipper> children) {
        Shipper shipper = new Shipper();
        shipper.setChildren(children);
        shipper.setParent(parent);
        return shipper;
    }

}
