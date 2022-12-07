import java.io.*;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;
public class Main {
    private static final String URL="jdbc:mysql://localhost:3306/example";
    private static final String USERNAME="root";
    private static final String PASSWORD="12345";
    public static void main(String[] args) {
        String url="INSERT INTO coffee(coffeename,coffeeaverage,coffeeimage) VALUES(?,?,?);";
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement statement=connection.prepareStatement(url)) {
            statement.setString(1,"India");
            statement.setInt(2,12);
            File file =new File("C:/Users/USER/Desktop/images/coffee2.jpg");
            FileInputStream in=new FileInputStream(file);
            statement.setBlob(3,in,file.length());
            statement.executeUpdate();
        }
        catch(FileNotFoundException e )
        {
            e.printStackTrace();
            System.out.println ("NO image ");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }*/
        String url1="SELECT * FROM coffee ;";
        try(Connection connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);PreparedStatement statement=connection.prepareStatement(url1))
        {
            byte b[];
            Blob blob;
            File file=new File("C:/Users/USER/Desktop/images/images2");
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            ResultSet resulSet=statement.executeQuery();
            while(resulSet.next())
            {
                blob=resulSet.getBlob("coffeeimage");
                b=blob.getBytes(1,(int)blob.length());
                fileOutputStream.write(b);
                System.out.println(fileOutputStream);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}