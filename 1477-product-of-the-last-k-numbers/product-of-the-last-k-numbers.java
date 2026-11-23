class ProductOfNumbers {
    List<Integer> li;

    public ProductOfNumbers() {
        li=new ArrayList<>();
    }
    
    public void add(int num) {
         li.add(num);
    }
    
    public int getProduct(int k) {
        int pro=1;
        int i=li.size()-1;
        while(k-- > 0){
            pro*=li.get(i);
            i--;
        }
        return pro;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */