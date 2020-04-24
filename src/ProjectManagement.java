import java.util.*;

public class ProjectManagement {
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        Map<String, Node> nodeMap = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        int count = s.nextInt();
        String name;
        int dur;
        Node node;
        for(int i = 0 ; i < count; i++){
            name = s.next();
            dur = s.nextInt();
            node = new Node(name, dur);
            nodeList.add(node);
            nodeMap.put(name, node);
        }
        int parentCount;
        for(int i = 0 ; i < count; i++){
            parentCount = s.nextInt();
            for(int j = 0; j < parentCount; j++){
                name = s.next();
                nodeList.get(i).addParents(nodeMap.get(name));
                nodeMap.get(name).addChildren(nodeList.get(i));
            }
            if(parentCount == 0)
                nodeList.get(i).setEarlyStart(0);
        }
        List<Node> finishNodeList = new ArrayList<>();
        for(int i = 0; i < count; i++)
            if(nodeList.get(i).children.size() == 0)
                finishNodeList.add(nodeList.get(i));

        if(finishNodeList.isEmpty()){
            System.out.println("No finish nodes");
            return;
        }

        node = finishNodeList.get(0);
        int nodeValue;
        int lateFinish = node.getEarlyStart() + node.duration;
        for(int i = 1; i < finishNodeList.size(); i++){
            node = finishNodeList.get(i);
            nodeValue = node.getEarlyStart() + node.duration;
            if(nodeValue > lateFinish)
                lateFinish = nodeValue;
        }

        for(Node n : finishNodeList)
            n.setLateFinish(lateFinish);

        for(Node n : nodeList)
            if(n.parents.isEmpty())
                n.getLateFinish();

        for(Node n : nodeList)
            System.out.println(n.toString());


    }
}

class Node{
    String name;
    List<Node> parents, children;
    int earlyStart=-1, earlyFinish=-1, lateStart=-1, lateFinish=-1, duration;
    Node(String name, int duration){
        this.name = name;
        this.duration = duration;
        parents = new ArrayList<>();
        children = new ArrayList<>();
    }
    void addParents(Node ... nodes){
        for(Node node : nodes)
            parents.add(node);
    }
    void addChildren(Node ... nodes){
        for(Node node : nodes)
            children.add(node);
    }
    int getEarlyStart(){
        if(earlyStart < 0 && !parents.isEmpty()){
            Node node = parents.get(0);
            int nodeValue;
            int max = node.getEarlyStart() + node.duration;
            for(int i = 1; i < parents.size(); i++){
                node = parents.get(i);
                nodeValue = node.getEarlyStart() + node.duration;
                if(nodeValue > max)
                    max = nodeValue;
            }
            setEarlyStart(max);
        }
        return earlyStart;
    }

    int getLateFinish(){
        if(lateFinish < 0 && !children.isEmpty()){
            Node node = children.get(0);
            int nodeValue;
            int min = node.getLateFinish() - node.duration;
            for(int i = 1; i < children.size(); i++){
                node = children.get(i);
                nodeValue = node.getLateFinish() - node.duration;
                if(nodeValue < min)
                    min = nodeValue;
            }
            setLateFinish(min);
        }
        return lateFinish;
    }

    void setEarlyStart(int earlyStart){
        this.earlyStart = earlyStart;
        this.earlyFinish = earlyStart + duration;
    }

    void setLateFinish(int lateFinish){
        this.lateFinish = lateFinish;
        this.lateStart = lateFinish - duration;
    }

    int getTotalSlackTime(){
        return lateFinish - earlyFinish;
    }

    int getFreeSlackTime(){
        if(children.isEmpty())
            return getTotalSlackTime();
        Node node = children.get(0);
        int min = node.earlyStart;
        for(int i = 0 ; i < children.size(); i++){
            if(children.get(i).earlyStart < min)
                min = children.get(i).earlyStart;
        }
        return min-earlyFinish;
    }

    boolean isCritical(){
        return earlyStart == lateStart && earlyFinish == lateFinish;
    }
    @Override
    public String toString() {
        return (isCritical()?"*":" ")+"{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", earlyStart=" + earlyStart +
                ", earlyFinish=" + earlyFinish +
                ", lateStart=" + lateStart +
                ", lateFinish=" + lateFinish +
                ", totalSlackTime=" + getTotalSlackTime() +
                ", freeSlackTime=" + getFreeSlackTime() +
                "}";
    }
}
