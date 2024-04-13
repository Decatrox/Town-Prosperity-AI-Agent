package code;

public class Node {
    private ConstantData con;
    private byte prosperity;
    private int totalMoneySpent;
    private byte food;
    private byte materials;
    private byte energy;
    private byte delay;
    private int depth;
    private Node parent;
    private String operator;
    private int pathCost;
    private int heuristicCost;

    private boolean requestedFood;
    private boolean requestedMaterials;
    private boolean requestedEnergy;







    public Node(byte initialProsperity, byte initialFood, byte initialMaterials, byte initialEnergy, ConstantData con) {
        this.con = con;
        this.prosperity = initialProsperity;
        this.totalMoneySpent = 0; //Change inside
        this.food = initialFood;
        this.materials = initialMaterials;
        this.energy = initialEnergy;
        this.delay = 0;
        this.depth = 0;
        this.parent = null;
        this.operator = "Initial";
        this.pathCost = 0;
        this.heuristicCost = 0;

        this.requestedFood = false;
        this.requestedMaterials =false;
        this.requestedEnergy=false;
    }


    private boolean isValidState(int totalMoneySpent, int food, int materials, int energy) {
        return (food >= 0 && materials >= 0 && energy >= 0 && totalMoneySpent <= 100000);
        //Prosperity should not pass 100 I believe as was told by a TA on piazza (Seen in MET1 whatsapp group)
        //maximum is 50 remember in new child
    }

    public Node requestFood(){
        //check logic of money spending
        //Resource changes
        int newTotalMoneySpent = this.totalMoneySpent + con.unitPriceFood() + con.unitPriceMaterials() + con.unitPriceEnergy();
        byte newFood = (byte)(this.food - 1);
        byte newMaterials = (byte)(this.materials - 1);
        byte newEnergy = (byte)(this.energy - 1);

        if(isValidState(newTotalMoneySpent,newFood,newMaterials,newEnergy) && this.delay == 0){
            if(requestedFood){
                newFood = (byte) Math.min(50, con.amountRequestFood() + newFood);
            }
            else if (requestedMaterials){
                newMaterials  = (byte) Math.min(50, con.amountRequestMaterials() + newMaterials);
            }
            else if(requestedEnergy){
                newEnergy  = (byte) Math.min(50, con.amountRequestEnergy() + newEnergy);
            }

            Node child = new Node(this.prosperity, newFood, newMaterials, newEnergy,con);

            child.setTotalMoneySpent(newTotalMoneySpent);
            child.setDelay(con.delayRequestFood());
            child.setDepth(this.depth + 1);
            child.setParent(this);
            child.setOperator("RequestFood");
            child.setPathCost(newTotalMoneySpent);
            child.setRequestedFood(true);
            child.setRequestedMaterials(false);
            child.setRequestedEnergy(false);
            return child;
        }
        else{
            return null;
        }
    }


    public Node requestMaterials(){
        int newTotalMoneySpent = this.totalMoneySpent + this.con.unitPriceFood() + con.unitPriceMaterials() + con.unitPriceEnergy();
        byte newFood = (byte)(this.food - 1);
        byte newMaterials = (byte)(this.materials - 1);
        byte newEnergy = (byte)(this.energy - 1);

        if(isValidState(newTotalMoneySpent,newFood,newMaterials,newEnergy) && this.delay == 0){
            if(requestedFood){
                newFood = (byte) Math.min(50, con.amountRequestFood() + newFood);
            }
            else if (requestedMaterials){
                newMaterials  = (byte) Math.min(50, con.amountRequestMaterials() + newMaterials);
            }
            else if(requestedEnergy){
                newEnergy  = (byte) Math.min(50, con.amountRequestEnergy() + newEnergy);
            }

            Node child = new Node(this.prosperity, newFood, newMaterials, newEnergy,con);

            child.setTotalMoneySpent(newTotalMoneySpent);
            child.setDelay(con.delayRequestMaterials());
            child.setDepth(this.depth + 1);
            child.setParent(this);
            child.setOperator("RequestMaterials");
            child.setPathCost(newTotalMoneySpent);
            child.setRequestedFood(false);
            child.setRequestedMaterials(true);
            child.setRequestedEnergy(false);

            return child;
        }
        else{
            return null;
        }
    }

    public Node requestEnergy(){
        int newTotalMoneySpent = this.totalMoneySpent + this.con.unitPriceFood() + con.unitPriceMaterials() + con.unitPriceEnergy();
        byte newFood = (byte)(this.food - 1);
        byte newMaterials = (byte)(this.materials - 1);
        byte newEnergy = (byte)(this.energy - 1);

        if(isValidState(newTotalMoneySpent,newFood,newMaterials,newEnergy) && this.delay == 0){
            if(requestedFood){
                newFood = (byte) Math.min(50, con.amountRequestFood() + newFood);
            }
            else if (requestedMaterials){
                newMaterials  = (byte) Math.min(50, con.amountRequestMaterials() + newMaterials);
            }
            else if(requestedEnergy){
                newEnergy  = (byte) Math.min(50, con.amountRequestEnergy() + newEnergy);
            }

            Node child = new Node(this.prosperity, newFood, newMaterials, newEnergy,con);

            child.setTotalMoneySpent(newTotalMoneySpent);
            child.setDelay(con.delayRequestEnergy());
            child.setDepth(this.depth + 1);
            child.setParent(this);
            child.setOperator("RequestEnergy");
            child.setPathCost(newTotalMoneySpent);
            child.setRequestedFood(false);
            child.setRequestedMaterials(false);
            child.setRequestedEnergy(true);

            return child;
        }
        else{
            return null;
        }
    }

    public Node Wait(){
        int newTotalMoneySpent = this.totalMoneySpent + this.con.unitPriceFood() + con.unitPriceMaterials() + con.unitPriceEnergy();
        byte newFood = (byte)(this.food - 1);
        byte newMaterials = (byte)(this.materials - 1);
        byte newEnergy = (byte)(this.energy - 1);


        if(isValidState(newTotalMoneySpent,newFood,newMaterials,newEnergy) && this.delay > 0){
            if(this.delay == 1) {
                if(requestedFood){
                    newFood = (byte) Math.min(50, con.amountRequestFood() + newFood);
                }
                else if (requestedMaterials){
                    newMaterials  = (byte) Math.min(50, con.amountRequestMaterials() + newMaterials);
                }
                else if(requestedEnergy){
                    newEnergy  = (byte) Math.min(50, con.amountRequestEnergy() + newEnergy);
                }
            }
            Node child = new Node(this.prosperity, newFood, newMaterials, newEnergy,con);

            child.setTotalMoneySpent(newTotalMoneySpent);
            child.setDelay((byte) (this.delay - 1));
            child.setDepth(this.depth + 1);
            child.setParent(this);
            child.setOperator("WAIT");
            child.setPathCost(newTotalMoneySpent);
            if(this.delay == 1){
                child.setRequestedFood(false);
                child.setRequestedMaterials(false);
                child.setRequestedEnergy(false);
            }
            else{
                child.setRequestedFood(this.requestedFood);
                child.setRequestedMaterials(this.requestedMaterials);
                child.setRequestedEnergy(this.requestedEnergy);
            }


            return child;
        }
        else{
            return null;
        }
    }

    public Node build1(){
        int newTotalMoneySpent = this.totalMoneySpent + con.priceBuild1() +
                con.foodUseBuild1() * con.unitPriceFood() +
                con.materialsUseBuild1() * con.unitPriceMaterials() +
                con.energyUseBuild1() * con.unitPriceEnergy();

        byte newFood = (byte)(this.food - con.foodUseBuild1());
        byte newMaterials = (byte)(this.materials -  con.materialsUseBuild1());
        byte newEnergy = (byte)(this.energy - con.energyUseBuild1());

        //Does delivery happen before starting the node?


        if(isValidState(newTotalMoneySpent,newFood,newMaterials,newEnergy)){
            if(this.delay == 1) {
                if(requestedFood){
                    newFood = (byte) Math.min(50, con.amountRequestFood() + newFood);
                }
                else if (requestedMaterials){
                    newMaterials  = (byte) Math.min(50, con.amountRequestMaterials() + newMaterials);
                }
                else if(requestedEnergy){
                    newEnergy  = (byte) Math.min(50, con.amountRequestEnergy() + newEnergy);
                }
            }

            byte newProsperity = (byte)(this.prosperity + con.prosperityBuild1());

            Node child = new Node(newProsperity, newFood, newMaterials, newEnergy,con);

            child.setTotalMoneySpent(newTotalMoneySpent);
            child.setDepth(this.depth + 1);
            child.setParent(this);
            child.setOperator("BUILD1");
            child.setPathCost(newTotalMoneySpent);
            if(this.delay == 1){
                child.setRequestedFood(false);
                child.setRequestedMaterials(false);
                child.setRequestedEnergy(false);
                child.setDelay((byte) 0);
            }
            else{
                child.setDelay((byte) Math.max(this.delay - 1, 0));
                child.setRequestedFood(this.requestedFood);
                child.setRequestedMaterials(this.requestedMaterials);
                child.setRequestedEnergy(this.requestedEnergy);
            }

            return child;
        }
        else{
            return null;
        }
    }

    public Node build2(){
        int newTotalMoneySpent = this.totalMoneySpent + con.priceBuild2() +
                con.foodUseBuild2() * con.unitPriceFood() +
                con.materialsUseBuild2() * con.unitPriceMaterials() +
                con.energyUseBuild2() * con.unitPriceEnergy();

        byte newFood = (byte)(this.food - con.foodUseBuild2());
        byte newMaterials = (byte)(this.materials -  con.materialsUseBuild2());
        byte newEnergy = (byte)(this.energy - con.energyUseBuild2());

        if(isValidState(newTotalMoneySpent,newFood,newMaterials,newEnergy)){
            //Does delivery happen before starting the node?
            if(this.delay == 1) {
                if(requestedFood){
                    newFood = (byte) Math.min(50, con.amountRequestFood() + newFood);
                }
                else if (requestedMaterials){
                    newMaterials  = (byte) Math.min(50, con.amountRequestMaterials() + newMaterials);
                }
                else if(requestedEnergy){
                    newEnergy  = (byte) Math.min(50, con.amountRequestEnergy() + newEnergy);
                }
            }

            byte newProsperity = (byte)(this.prosperity + con.prosperityBuild2());

            Node child = new Node(newProsperity, newFood, newMaterials, newEnergy,con);

            child.setTotalMoneySpent(newTotalMoneySpent);
            child.setDepth(this.depth + 1);
            child.setParent(this);
            child.setOperator("BUILD2");
            child.setPathCost(newTotalMoneySpent);
            if(this.delay == 1){
                child.setRequestedFood(false);
                child.setRequestedMaterials(false);
                child.setRequestedEnergy(false);
                child.setDelay((byte)0);
            }
            else{
                child.setDelay((byte) Math.max(this.delay - 1, 0));
                child.setRequestedFood(this.requestedFood);
                child.setRequestedMaterials(this.requestedMaterials);
                child.setRequestedEnergy(this.requestedEnergy);
            }
            return child;
        }
        else{
            return null;
        }
    }

    public String getState() {
        return "State{prosperity=" + this.prosperity + ", food=" + this.food + ", materials=" + this.materials
                + ", energy=" + this.energy + ", money_spent=" + this.totalMoneySpent + "}" + "\n";
    }

    public String formString() {
        String s = this.getProsperity() + ";" + this.getFood() + ";" + this.getMaterials() + ";"
                + this.getEnergy() + ";" + this.getDelay() + ";" + this.getOperator() + ";" + this.isRequestedFood()
                + ";" + this.isRequestedMaterials() + ";" + this.isRequestedEnergy() + ";" + this.getHeuristicCost()
                + ";" + this.getTotalMoneySpent();
        return s;
    }

    public String formStringUCS() {
        String s = this.getProsperity() + ";" + this.getFood() + ";" + this.getMaterials() + ";"
                + this.getEnergy() + ";" + this.getDelay() + ";" + this.getOperator() + ";" + this.isRequestedFood()
                + ";" + this.isRequestedMaterials() + ";" + this.isRequestedEnergy() + ";" + this.getHeuristicCost();
        return s;
    }

    // public void normalizaPathCosts(){
    //     int a = con.unitPriceFood() + con.unitPriceMaterials() + con.unitPriceEnergy();
    //     int b = con.priceBuild1() +
    //             con.foodUseBuild1() * con.unitPriceFood() +
    //             con.materialsUseBuild1() * con.unitPriceMaterials() +
    //             con.energyUseBuild1() * con.unitPriceEnergy();
    //     int c = con.priceBuild2() +
    //             con.foodUseBuild2() * con.unitPriceFood() +
    //             con.materialsUseBuild2() * con.unitPriceMaterials() +
    //             con.energyUseBuild2() * con.unitPriceEnergy();

    //     int minPathCost = Math.min(Math.min(a, b), c);
    //     int maxPathCost = Math.max(Math.max(a, b), c);

    //     int d = (this.pathCost - minPathCost) / (maxPathCost - minPathCost) * 100;
    //     this.setPathCost(d);
    // }

    //GETTERS AND SETTERS

    public ConstantData getCon() {
        return con;
    }

    public void setCon(ConstantData con) {
        this.con = con;
    }

    public byte getProsperity() {
        return prosperity;
    }

    public void setProsperity(byte prosperity) {
        this.prosperity = prosperity;
    }

    public int getTotalMoneySpent() {
        return totalMoneySpent;
    }

    public void setTotalMoneySpent(int totalMoneySpent) {
        this.totalMoneySpent = totalMoneySpent;
    }

    public byte getFood() {
        return food;
    }

    public void setFood(byte food) {
        this.food = food;
    }

    public byte getMaterials() {
        return materials;
    }

    public void setMaterials(byte materials) {
        this.materials = materials;
    }

    public byte getEnergy() {
        return energy;
    }

    public void setEnergy(byte energy) {
        this.energy = energy;
    }

    public byte getDelay() {
        return delay;
    }

    public void setDelay(byte delay) {
        this.delay = delay;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public boolean isRequestedFood() {
        return requestedFood;
    }

    public void setRequestedFood(boolean requestedFood) {
        this.requestedFood = requestedFood;
    }

    public boolean isRequestedMaterials() {
        return requestedMaterials;
    }

    public void setRequestedMaterials(boolean requestedMaterials) {
        this.requestedMaterials = requestedMaterials;
    }

    public boolean isRequestedEnergy() {
        return requestedEnergy;
    }

    public void setRequestedEnergy(boolean requestedEnergy) {
        this.requestedEnergy = requestedEnergy;
    }
}
