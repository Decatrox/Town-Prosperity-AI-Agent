package code;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

public class GenericSearch {
    static String res = "";
    static int count = 0;
    public static boolean depthReachedDepth = false;

    public static String GeneralSearch(Node initialNode, String searchType, boolean visualize) {
        if (searchType.equals("BF")) {
            return BreadthFirstSearch(initialNode, visualize);
        } else if (searchType.equals("DF")) {
            return DepthFirstSearch(initialNode, visualize);
        } else if (searchType.equals("ID")) {
            return IterativeDeepeningSearch(initialNode, visualize);
        } else if (searchType.equals("UC")) {
            return UniformCostSearch(initialNode, visualize);
        }else if (searchType.equals("GR1")) {
            return GreedyOneSearch(initialNode, visualize);
        }else if (searchType.equals("GR2")) {
            return GreedyTwoSearch(initialNode, visualize);
        }else if (searchType.equals("AS1")) {
            return AStarOneSearch(initialNode, visualize);
        }else if (searchType.equals("AS2")) {
            return AStarTwoSearch(initialNode, visualize);
        } else {
            return "Invalid search type.";
        }
    }

    private static String BreadthFirstSearch(Node initialNode, boolean visualize) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(initialNode);
        HashSet<String> visitedNodes = new HashSet<>();
        count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!visitedNodes.contains(node.formString())) {
                count++;
                visitedNodes.add(node.formString());
                if (visualize) {
                    System.out.println(node.getDepth());
                    System.out.println(node.getOperator());
                    System.out.println(node.getState());
                }
                // Check if prosperity reaches 100
                if (node.getProsperity() >= 100) {
                    int money = node.getTotalMoneySpent();
                    StringBuilder path = new StringBuilder();
                    Boolean flag = false;
                    while (node.getParent() != null) {
                        if (!flag) {
                            path.insert(0, node.getOperator());
                            flag = true;
                        } else {
                            path.insert(0, node.getOperator() + ",");
                        }
                        node = node.getParent();
                    }
                    return path.toString() + ";" + money + ";" + count;
                }

                // Add child nodes to the queue
                Node child1 = node.requestFood();
                Node child2 = node.requestMaterials();
                Node child3 = node.requestEnergy();
                Node child4 = node.build1();
                Node child5 = node.build2();
                Node child6 = node.Wait();

                if (child1 != null)
                    queue.add(child1);
                if (child2 != null)
                    queue.add(child2);
                if (child3 != null)
                    queue.add(child3);
                if (child4 != null)
                    queue.add(child4);
                if (child5 != null)
                    queue.add(child5);
                if (child6 != null)
                    queue.add(child6);
            }
        }
        return "NOSOLUTION";
    }

    private static String DepthFirstSearch(Node initialNode, boolean visualize) {
        Stack<Node> stack = new Stack<>();
        stack.push(initialNode);
        HashSet<String> visitedNodes = new HashSet<>();
        count = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (!visitedNodes.contains(node.formString())) {
                count++;
                visitedNodes.add(node.formString());
                if (visualize) {
                    System.out.println(node.getOperator());
                    System.out.println(node.getState());
                }
                // Check if prosperity reaches 100
                if (node.getProsperity() >= 100) {

                    int money = node.getTotalMoneySpent();
                    StringBuilder path = new StringBuilder();
                    Boolean flag = false;
                    while (node.getParent() != null) {
                        if (!flag) {
                            path.insert(0, node.getOperator());
                            flag = true;
                        } else {
                            path.insert(0, node.getOperator() + ",");
                        }
                        node = node.getParent();
                    }
                    return path.toString() + ";" + money + ";" + count;
                }
                // Check if all resources are finished
                if (node.getFood() <= 0 && node.getMaterials() <= 0 && node.getEnergy() <= 0) {
                    return "NOSOLUTION";
                }

                // Add child nodes to the stack in reverse order (for depth-first)
                Node child1 = node.requestFood();
                Node child2 = node.requestMaterials();
                Node child3 = node.requestEnergy();
                Node child4 = node.build1();
                Node child5 = node.build2();
                Node child6 = node.Wait();


                if (child6 != null)
                    stack.push(child6);
                if (child5 != null)
                    stack.push(child5);
                if (child4 != null)
                    stack.push(child4);
                if (child3 != null)
                    stack.push(child3);
                if (child2 != null)
                    stack.push(child2);
                if (child1 != null)
                    stack.push(child1);
            }
            
        }
        return "NOSOLUTION";
    }

    private static String DepthLimitedSearch(Node initialNode, int depth, boolean visualize) {
        Stack<Node> stack = new Stack<>();
        stack.push(initialNode);
        HashSet<String> visitedNodes = new HashSet<>();
        count = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (!visitedNodes.contains(node.formString())) {
                count++;
                visitedNodes.add(node.formString());
                int depthNode = node.getDepth();

                if (depthNode > depth) {
                    return "";
                }
                if (visualize) {
                    System.out.println(node.getOperator());
                    System.out.println(node.getState());
                }
                // Check if prosperity reaches 100
                if (node.getProsperity() >= 100) {
                    depthReachedDepth = true;
                    int money = node.getTotalMoneySpent();
                    StringBuilder path = new StringBuilder();
                    Boolean flag = false;
                    while (node.getParent() != null) {
                        if (!flag) {
                            path.insert(0, node.getOperator());
                            flag = true;
                        } else {
                            path.insert(0, node.getOperator() + ",");
                        }
                        node = node.getParent();
                    }
                    return path.toString() + ";" + money + ";" + count;
                }

                // Add child nodes to the stack in reverse order (for depth-first)
                Node child1 = node.requestFood();
                Node child2 = node.requestMaterials();
                Node child3 = node.requestEnergy();
                Node child4 = node.build1();
                Node child5 = node.build2();
                Node child6 = node.Wait();

                if (child6 != null)
                    stack.push(child6);
                if (child5 != null)
                    stack.push(child5);
                if (child4 != null)
                    stack.push(child4);
                if (child3 != null)
                    stack.push(child3);
                if (child2 != null)
                    stack.push(child2);
                if (child1 != null)
                    stack.push(child1);
            }
        }
        return "NOSOLUTION";
    }

    private static String IterativeDeepeningSearch(Node initialNode, boolean visualize) {
        int depth = 0;
        depthReachedDepth = false;
        StringBuilder result = new StringBuilder();
        while (true) {

            String temp_result = DepthLimitedSearch(initialNode, depth, visualize);
            if (temp_result.equals("NOSOLUTION")) {
                return "NOSOLUTION";
            } else {
                result.append(temp_result);
            }
            if (depthReachedDepth) {

                break;
            }
            depth++;
        }
        return result.toString();
    }

    private static String UniformCostSearch(Node initialNode, boolean visualize) {
        res = "";
        Comparator<Node> costComparator = Comparator.comparingInt(Node::getPathCost);

        // Create a priority queue with the custom comparator
        PriorityQueue<Node> queue = new PriorityQueue<>(costComparator);

        queue.add(initialNode);
        HashSet<String> visitedNodes = new HashSet<>();

        count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!visitedNodes.contains(node.formStringUCS())) {
                count++;
                visitedNodes.add(node.formStringUCS());
            
            if (visualize) {
                System.out.println(node.getOperator());
                System.out.println(node.getState());
            }
            // Check if prosperity reaches 100
            if (node.getProsperity() >= 100) {
            	int money=node.getTotalMoneySpent();
            	StringBuilder path = new StringBuilder();
            	Boolean flag=false;
                while (node.getParent() != null) {
                	if(!flag) {
                		path.insert(0, node.getOperator());
                        flag=true;
                	}else {
                		path.insert(0, node.getOperator() + ",");
                	}
                    node = node.getParent();
                }
                return path.toString()+ ";" + money + ";" + count;
            }

            // Add child nodes to the queue
            Node child1 = node.requestFood();
            Node child2 = node.requestMaterials();
            Node child3 = node.requestEnergy();
            Node child4 = node.build1();
            Node child5 = node.build2();
            Node child6 = node.Wait();

            if (child1 != null) {
                queue.add(child1);
            }
            if (child2 != null)
                queue.add(child2);
            if (child3 != null)
                queue.add(child3);
            if (child4 != null)
                queue.add(child4);
            if (child5 != null)
                queue.add(child5);
            if (child6 != null)
                queue.add(child6);
        }
    }
        return "NOSOLUTION";
    }

    private static String GreedyOneSearch(Node initialNode, boolean visualize){
        Comparator<Node> nodeComparator = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                // Sort in ascending order of heuristic cost
                return Integer.compare(node1.getHeuristicCost(), node2.getHeuristicCost());
            }
        };

        PriorityQueue<Node> queue = new PriorityQueue<>(nodeComparator);
        queue.add(initialNode);
        HashSet<String> visitedNodes = new HashSet<>();
        count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!visitedNodes.contains(node.formString())) {
                count++;
                visitedNodes.add(node.formString());
                if (visualize) {
                    System.out.println(node.getOperator());
                    System.out.println(node.getState());
                }
                // Check if prosperity reaches 100
                if (node.getProsperity() >= 100) {
                    int money=node.getTotalMoneySpent();
                    StringBuilder path = new StringBuilder();
                    Boolean flag=false;
                    while (node.getParent() != null) {
                        if(!flag) {
                            path.insert(0, node.getOperator());
                            flag=true;
                        }else {
                            path.insert(0, node.getOperator() + ",");
                        }
                        node = node.getParent();
                    }
                    return path.toString()+ ";" + money + ";" + count;
                }

                Node child1 = node.requestFood();
                Node child2 = node.requestMaterials();
                Node child3 = node.requestEnergy();
                Node child4 = node.build1();
                Node child5 = node.build2();
                Node child6 = node.Wait();

                //First Heuristic
                int costBuild1 = node.getCon().priceBuild1() +
                        node.getCon().foodUseBuild1() * node.getCon().unitPriceFood() +
                        node.getCon().materialsUseBuild1() * node.getCon().unitPriceMaterials() +
                        node.getCon().energyUseBuild1() * node.getCon().unitPriceEnergy();

                int costBuild2 = node.getCon().priceBuild2() +
                        node.getCon().foodUseBuild2() * node.getCon().unitPriceFood() +
                        node.getCon().materialsUseBuild2() * node.getCon().unitPriceMaterials() +
                        node.getCon().energyUseBuild2() * node.getCon().unitPriceEnergy();

                int minBuildCost = Integer.min(costBuild1, costBuild2);
                int maxProsperityInc = Integer.max(node.getCon().prosperityBuild1(),node.getCon().prosperityBuild2());

                //Heuristic is MinBuildsToGoal*MinBuildCost
                if (child1 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child1.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child1.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child1);
                }
                if (child2 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child2.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child2.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child2);
                }
                if (child3 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child3.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child3.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child3);
                }
                if (child4 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child4.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child4.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child4);
                }
                if (child5 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child5.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child5.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child5);
                }
                if (child6 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child6.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child6.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child6);
                }
            }
        }
        return "NOSOLUTION";
    }

    private static String GreedyTwoSearch(Node initialNode, boolean visualize){
        Comparator<Node> nodeComparator = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                // Sort in ascending order of heuristic cost
                return Integer.compare(node1.getHeuristicCost(), node2.getHeuristicCost());
            }
        };

        PriorityQueue<Node> queue = new PriorityQueue<>(nodeComparator);
        queue.add(initialNode);
        HashSet<String> visitedNodes = new HashSet<>();
        count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!visitedNodes.contains(node.formString())) {
                count++;
                visitedNodes.add(node.formString());
                if (visualize) {
                    System.out.println(node.getOperator());
                    System.out.println(node.getState());
                }
                // Check if prosperity reaches 100
                if (node.getProsperity() >= 100) {
                    int money=node.getTotalMoneySpent();
                    StringBuilder path = new StringBuilder();
                    Boolean flag=false;
                    while (node.getParent() != null) {
                        if(!flag) {
                            path.insert(0, node.getOperator());
                            flag=true;
                        }else {
                            path.insert(0, node.getOperator() + ",");
                        }

                        node = node.getParent();
                    }
                    return path.toString()+ ";" + money + ";" + count;
                }

                Node child1 = node.requestFood();
                Node child2 = node.requestMaterials();
                Node child3 = node.requestEnergy();
                Node child4 = node.build1();
                Node child5 = node.build2();
                Node child6 = node.Wait();

                //Second Heuristic
                int costEnergyBuild1 = node.getCon().energyUseBuild1() * node.getCon().unitPriceEnergy();

                int costEnergyBuild2 = node.getCon().energyUseBuild2() * node.getCon().unitPriceEnergy();

                int minBuildCost = Integer.min(costEnergyBuild1, costEnergyBuild2);

                int maxProsperityInc = Integer.max(node.getCon().prosperityBuild1(),node.getCon().prosperityBuild2());

                //Heuristic is MinBuildsToGoal*MinBuildCostUsingEnergy
                if (child1 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child1.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child1.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child1);
                }
                if (child2 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child2.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child2.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child2);
                }
                if (child3 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child3.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child3.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child3);
                }
                if (child4 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child4.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child4.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child4);
                }
                if (child5 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child5.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child5.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child5);
                }
                if (child6 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child6.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child6.setHeuristicCost(minBuildCost*minBuildsToGoal);
                    queue.add(child6);
                }
            }
        }
        return "NOSOLUTION";
    }

    private static String AStarOneSearch(Node initialNode, boolean visualize) {
        Comparator<Node> nodeComparator = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                // Sort in ascending order of heuristic cost
                return Integer.compare(node1.getHeuristicCost(), node2.getHeuristicCost());
            }
        };

        PriorityQueue<Node> queue = new PriorityQueue<>(nodeComparator);
        queue.add(initialNode);
        HashSet<String> visitedNodes = new HashSet<>();
        count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!visitedNodes.contains(node.formStringUCS())) {
                count++;
                visitedNodes.add(node.formStringUCS());
                if (visualize) {
                    System.out.println(node.getOperator());
                    System.out.println(node.getState());
                }
                // Check if prosperity reaches 100
                if (node.getProsperity() >= 100) {
                    int money = node.getTotalMoneySpent();
                    StringBuilder path = new StringBuilder();
                    Boolean flag = false;
                    while (node.getParent() != null) {
                        if (!flag) {
                            path.insert(0, node.getOperator());
                            flag = true;
                        } else {
                            path.insert(0, node.getOperator() + ",");
                        }
                        node = node.getParent();
                    }
                    return path.toString() + ";" + money + ";" + count;
                }

                Node child1 = node.requestFood();
                Node child2 = node.requestMaterials();
                Node child3 = node.requestEnergy();
                Node child4 = node.build1();
                Node child5 = node.build2();
                Node child6 = node.Wait();

                int costBuild1 = node.getCon().priceBuild1() +
                        node.getCon().foodUseBuild1() * node.getCon().unitPriceFood() +
                        node.getCon().materialsUseBuild1() * node.getCon().unitPriceMaterials() +
                        node.getCon().energyUseBuild1() * node.getCon().unitPriceEnergy();

                int costBuild2 = node.getCon().priceBuild2() +
                        node.getCon().foodUseBuild2() * node.getCon().unitPriceFood() +
                        node.getCon().materialsUseBuild2() * node.getCon().unitPriceMaterials() +
                        node.getCon().energyUseBuild2() * node.getCon().unitPriceEnergy();

                int minBuildCost = Integer.min(costBuild1, costBuild2);
                int maxProsperityInc = Integer.max(node.getCon().prosperityBuild1(),node.getCon().prosperityBuild2());
                //Heuristic is MinBuildsToGoal*MinBuildCost
                if (child1 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child1.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child1.setHeuristicCost(minBuildCost*minBuildsToGoal + child1.getPathCost());
                    queue.add(child1);
                }
                if (child2 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child2.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child2.setHeuristicCost(minBuildCost*minBuildsToGoal + child2.getPathCost());
                    queue.add(child2);
                }
                if (child3 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child3.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child3.setHeuristicCost(minBuildCost*minBuildsToGoal + child3.getPathCost());
                    queue.add(child3);
                }
                if (child4 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child4.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child4.setHeuristicCost(minBuildCost*minBuildsToGoal + child4.getPathCost());
                    queue.add(child4);
                }
                if (child5 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child5.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child5.setHeuristicCost(minBuildCost*minBuildsToGoal + child5.getPathCost());
                    queue.add(child5);
                }
                if (child6 != null){
                    int minBuildsToGoal = (int) Math.ceil((100-child6.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child6.setHeuristicCost(minBuildCost*minBuildsToGoal + child6.getPathCost());
                    queue.add(child6);
                }
            }
        }
        return "NOSOLUTION";
    }

    private static String AStarTwoSearch(Node initialNode, boolean visualize){
        Comparator<Node> nodeComparator = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                // Sort in ascending order of heuristic cost
                return Integer.compare(node1.getHeuristicCost(), node2.getHeuristicCost());
            }
        };

        PriorityQueue<Node> queue = new PriorityQueue<>(nodeComparator);
        queue.add(initialNode);
        HashSet<String> visitedNodes = new HashSet<>();
        count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!visitedNodes.contains(node.formStringUCS())) {
                count++;
                visitedNodes.add(node.formStringUCS());
                if (visualize) {
                    System.out.println(node.getOperator());
                    System.out.println(node.getState());
                }
                // Check if prosperity reaches 100
                if (node.getProsperity() >= 100) {
                    int money = node.getTotalMoneySpent();
                    StringBuilder path = new StringBuilder();
                    Boolean flag = false;
                    while (node.getParent() != null) {
                        if (!flag) {
                            path.insert(0, node.getOperator());
                            flag = true;
                        } else {
                            path.insert(0, node.getOperator() + ",");
                        }
                        node = node.getParent();
                    }
                    return path.toString() + ";" + money + ";" + count;
                }

                Node child1 = node.requestFood();
                Node child2 = node.requestMaterials();
                Node child3 = node.requestEnergy();
                Node child4 = node.build1();
                Node child5 = node.build2();
                Node child6 = node.Wait();

                int costEnergyBuild1 = node.getCon().energyUseBuild1() * node.getCon().unitPriceEnergy();

                int costEnergyBuild2 = node.getCon().energyUseBuild2() * node.getCon().unitPriceEnergy();

                int minBuildCost = Integer.min(costEnergyBuild1, costEnergyBuild2);

                int maxProsperityInc = Integer.max(node.getCon().prosperityBuild1(),node.getCon().prosperityBuild2());

                //Heuristic is MinBuildsToGoal*MinBuildCostUsingEnergy
                if (child1 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child1.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child1.setHeuristicCost(minBuildCost*minBuildsToGoal + child1.getPathCost());
                    queue.add(child1);
                }
                if (child2 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child2.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child2.setHeuristicCost(minBuildCost*minBuildsToGoal + child2.getPathCost());
                    queue.add(child2);
                }
                if (child3 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child3.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child3.setHeuristicCost(minBuildCost*minBuildsToGoal + child3.getPathCost());
                    queue.add(child3);
                }
                if (child4 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child4.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child4.setHeuristicCost(minBuildCost*minBuildsToGoal + child4.getPathCost());
                    queue.add(child4);
                }
                if (child5 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child5.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child5.setHeuristicCost(minBuildCost*minBuildsToGoal + child5.getPathCost());
                    queue.add(child5);
                }
                if (child6 != null) {
                    int minBuildsToGoal = (int) Math.ceil((100-child6.getProsperity())/maxProsperityInc);
                    minBuildsToGoal = Math.max(minBuildsToGoal,0);
                    child6.setHeuristicCost(minBuildCost*minBuildsToGoal + child6.getPathCost());
                    queue.add(child6);
                }
            }
        }
        return "NOSOLUTION";
    }

}
