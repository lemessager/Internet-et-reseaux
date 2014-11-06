package Server;

import java.util.*;

public class Data {

    private Map<String,ArrayList<String>> associations;

    public Data() {
        associations = new HashMap<String, ArrayList<String>>();
    }

    public Data(Map<String,ArrayList<String>> associations) {
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
        String name;
        Set keys = associations.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            name=(String)it.next();
            sb.append("Surnoms associés à ");
            sb.append(name);
            sb.append(" : \n");
            for (String surname : associations.get(name)) {
                sb.append("-");
                sb.append(surname);
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public HashMap<String,ArrayList<String>> getAssociations() {
        return new HashMap<String, ArrayList<String>>(associations);
    }

    public static void main(String[] args) {
        Data data = new Data();
        data.add("Riana", "Riri");
        data.add("Riana", "Franky");
        data.add("Claquin", "DawierDone");
        System.out.println(data.list());
    }
}
