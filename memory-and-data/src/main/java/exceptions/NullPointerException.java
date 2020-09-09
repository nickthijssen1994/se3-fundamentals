package exceptions;

public class NullPointerException {

    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("Nick");
        System.out.println(user1.getNameLength());
        User user2 = new User();
        System.out.println(user2.getNameLength());
    }

    static class User{
        private String name;

        public void setName(String name){
            this.name = name;
        }

        public int getNameLength(){
            return this.name.length();
        }
    }

}
