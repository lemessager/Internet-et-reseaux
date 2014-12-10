package server;

import java.util.*;

import error_codes.ErrorCode;

public class Data {

    private Map<String,ArrayList<String>> associations;

    public Data() {
        associations = new HashMap<String, ArrayList<String>>();
    }

    public Data(Map<String, ArrayList<String>> associations) {
        this.associations=associations;
    }

    public ErrorCode add(String name, String surname) {
        /*Collection<ArrayList<String>> surnamesLists = associations.values();
        Iterator it = surnamesLists.iterator();
        List l;
        while (it.hasNext()) {
            l=it.next();

        }*/
        if ((name != "") && (surname != "")) {
            for (ArrayList<String> list : associations.values()) {
                for (String s : list) {
                    if (s.equals(surname)) {
                        return ErrorCode.SURNAME_EXISTS;
                    }
                }
            }
            if (associations.containsKey(name)) {
                associations.get(name).add(surname);
            } else {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(surname);
                associations.put(name, newList);
            }
            return ErrorCode.NONE;
        }
        return ErrorCode.INVALID_ARGUMENT;
    }

    public Map<String, List<String>> list() {
        Map<String,List<String>> res=new HashMap<String, List<String>>();
        String name;
        Set keys = associations.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            name=(String)it.next();
            res.put(name,associations.get(name));
        }
        return res;
    }

    public Map<String,ArrayList<String>> getAssociations() {
        return new HashMap<String, ArrayList<String>>(associations);
    }
}
