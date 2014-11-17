package Server;

import java.util.*;

public class Donnees {

    private Map<String,ArrayList<String>> associations;

    public Donnees() {
        associations = new HashMap<String, ArrayList<String>>();
    }

    public Donnees(Map<String, ArrayList<String>> associations) {
        this.associations=associations;
    }

    public boolean add(String name, String surname) {
        if (name != "") {
            if (associations.containsKey(name)) {
                associations.get(name).add(surname);
            } else {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(surname);
                associations.put(name, newList);
            }
            return true;
        }
        return false;
    }

    public String list() {
        StringBuilder sb = new StringBuilder("");
        String newline = System.getProperty("line.separator");
        String name;
        Set keys = associations.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            name=(String)it.next();
            sb.append("Surnoms associés à ");
            sb.append(name);
            sb.append(" : "+newline);
            for (String surname : associations.get(name)) {
                sb.append("-");
                sb.append(surname);
                sb.append(newline);
            }
            sb.append(newline);
        }
        return sb.toString();
    }

    public HashMap<String,ArrayList<String>> getAssociations() {
        return new HashMap<String, ArrayList<String>>(associations);
    }
}
