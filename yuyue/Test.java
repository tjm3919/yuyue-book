
public class Test {

    public static void main(String[] args) {
    }


    public static void pdStr(){
        // 字符串str2是否包含字符串str1
        String str1="Microsoft Edge";
        String str2="cfqwsfvqw \"Microsoft Edge\" qwrfqwqw";
        if(str2.contains(str1)) {
            System.out.println("存在包含关系");
        }else {
            System.out.println("不存在包含关系");
        }
    }

    public static class Emp{
        int a;
        String b;

        public Emp(int a, String b) {
            this.a = a;
            this.b = b;
        }

        public Emp() {
            super();
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
