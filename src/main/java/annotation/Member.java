package annotation;

@DBTable(name = "MEMBER")
public class Member {
    @SQLString(30) String firstName;
    @SQLString(50) String lastName;
    @SQLInteger Integer age;

    @SQLString( value =30 ,constrains = @Constrains(primaryKey = true))
    String handle;

    static int memeberCount;

    public String getHandle(){ return handle;};
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    @Override
    public String toString(){ return handle;}
    public Integer getAge(){ return age;}

}
