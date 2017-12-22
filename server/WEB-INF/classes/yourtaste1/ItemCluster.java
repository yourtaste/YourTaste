package yourtaste1;

import java.io.FileWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.io.*;


public class ItemCluster {
	
	public static void DBConnect() throws IOException {
        Connection connection = null;
        Statement st = null;
        Statement stmt = null;
        Statement st2 = null;
        Statement stmt2 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yourtaste" , "root", "yourtaste2017");
            st = connection.createStatement();
            stmt = connection.createStatement();
            st2 = connection.createStatement();
            stmt2 = connection.createStatement();
 
            String sql;
            String sql2;
            
            sql = "SELECT * FROM yourtaste.foodsim";
            sql2 = "SELECT uid, fid, score FROM yourtaste.userscore order by uid";
 
            ResultSet rs = st.executeQuery(sql);
            int rowCount = 0;
            int ColCount = 0;
            rs.last();
            rowCount = rs.getRow();
            rs.beforeFirst();
            ResultSet re = stmt.executeQuery(sql);
            ResultSetMetaData md = re.getMetaData() ;
            ColCount = md.getColumnCount();

            String[][] array = null;
            array = new String[rowCount][ColCount]; 

            int a = 0;
            
            ResultSet rs2 = st2.executeQuery(sql2);
            int rowCount2 = 0;
            int ColCount2 = 0;
            rs2.last();
            rowCount2 = rs2.getRow();
            rs2.beforeFirst();
            ResultSet re2 = stmt2.executeQuery(sql2);
            ResultSetMetaData md2 = re2.getMetaData() ;
            ColCount2 = md2.getColumnCount();

            String[][] array2 = null;
            array2 = new String[rowCount2][ColCount2]; 

            int a2 = 0;
            
          //  System.out.println(rowCount);
          //  System.out.println(ColCount);
 
            while (rs.next()) {    
                array[a][0] = rs.getString("userid");
                array[a][1] = rs.getString("foodid1");
                array[a][2] = rs.getString("foodid2");
                array[a][3] = rs.getString("foodid3");
                array[a][4] = rs.getString("foodid4");
                array[a][5] = rs.getString("foodid5");
                
                a++;
                }
            
            while (rs2.next()) {    
                array2[a2][0] = rs2.getString("uid");
                array2[a2][1] = rs2.getString("fid");
                array2[a2][2] = rs2.getString("score");
                
                a2++;
                }
            
          /*  for (int i = 0; i < array.length; i++) {
            	System.out.println();
            	for (int j = 0; j < array[i].length; j++) {
                	System.out.print(array[i][j] + " ");
                	}
            	}
            
            System.out.println();
            for (int i = 0; i < array2.length; i++) {
            	System.out.println();
            	for (int j = 0; j < array2[i].length; j++) {
                	System.out.print(array2[i][j] + " ");
                	}
            	}
      
            
            System.out.println();*/

            float[][] array3 = new float[rowCount2][ColCount2];
            for(int i = 0;i<rowCount2;i++) {
            	for(int j = 0; j<ColCount2; j++) {
            		array3[i][j] = Float.parseFloat(array2[i][j]);            	
            		}
            	}
            /*for (int i = 0; i < array3.length; i++) {
            	System.out.println();
            	for (int j = 0; j < array3[i].length; j++) {
                	System.out.print(array3[i][j] + " ");
                	}
            	}*/
            

            rs.close();
            rs2.close();
            st.close();
            st2.close();
            connection.close();
            
            String createfile = "/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/yourtaste1/TasteData1.csv";
            String createfile2 = "/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/yourtaste1/TasteData2.csv";
            FileWriter fw = new FileWriter(createfile);
            FileWriter fw2 = new FileWriter(createfile2);
            
            for(int i = 0; i<rowCount; i++) {
            	fw.append(array[i][0]);
            	fw.append(',');
            	fw.append(array[i][1]);
            	fw.append(',');
            	fw.append(array[i][2]);
            	fw.append(',');
            	fw.append(array[i][3]);
            	fw.append(',');
            	fw.append(array[i][4]);
            	fw.append(',');
            	fw.append(array[i][5]);
            	fw.append('\n');
            }
            
            for(int i = 0; i<rowCount2; i++) {
            	fw2.append(array2[i][0]);
            	fw2.append(',');
            	fw2.append(array2[i][1]);
            	fw2.append(',');
            	fw2.append(array2[i][2]);
            	fw2.append('\n');
            		
            }
            
            fw.flush();
            fw.close();
            
            fw2.flush();
            fw2.close();
            
            } 
        catch(ClassNotFoundException|SQLException e) {
        	System.err.println("connect Fail!");
        	}
	}

	public static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
	
	public static String[][] Usersim(int DBUserId) throws IOException {

		String array[][] = null;
		int rowcount = countLines("/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/yourtaste1/TasteData1.csv");
		
		array = new String[rowcount][6];
				
		try {
			File csv = new File("/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/yourtaste1/TasteData1.csv");
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";
            int row =0 ,i;
            
            while ((line = br.readLine()) != null) {
                String[] token = line.split(",", -1);
                for(i=0;i<6;i++) {
                 array[row][i] = token[i];
                 if(array[row][i].equals("null")) {
                	 float from = 0;
                	 String to = Float.toString(from);
                	 array[row][i] = to;
                 }
                }
                
                for(i=0;i<6;i++) {
               //  System.out.print(array[row][i] + ",");
                }
              //  System.out.println("");
                
                row++;
            }
            br.close();
           // System.out.println(array[rowcount-1][0]);
            
            float allco = 0;
            float efn = 0;
            
            float tanimo[][] = null;
            tanimo = new float[rowcount][rowcount];
            
            for(int a = 0; a<(rowcount); a++) {
            	for(int b = 0; b<(rowcount); b++) {
            		for(int j= 1; j<6; j++) {
            			if(Float.parseFloat(array[a][j]) != 0 || Float.parseFloat(array[b][j]) != 0) {
            				allco++;
            				float N = (Float.parseFloat(array[a][j])) - (Float.parseFloat(array[b][j]));
            				if(Math.abs(N) <= 0.5 ) {
            					efn++;
            				}
            				else continue;
            			}
            		}
            		tanimo[a][b] = (efn/allco);

            		efn = 0; 
            		allco = 0;
            	}
            }
            
            int UserId = DBUserId - 1;
            float UserSim[][]= null;
            UserSim = new float[2][rowcount];

            for (int x = 0; x < tanimo.length; x++) {
                for (int k = 0; k < tanimo[x].length; k++) {
                 //   System.out.print(tanimo[x][k] + " ");
                }
             //   System.out.println();
            }
            
            for(int g = 0; g<rowcount; g++ ) {
            	UserSim[0][g] = tanimo[UserId][g];
            	UserSim[1][g] = g+1;
            }
            
            for (int g = 0; g < rowcount; g++) {
                for (int z = g + 1; z < rowcount; z++) {
                    if (UserSim[0][g] < UserSim[0][z]) {
                    	   float temp = UserSim[0][z];
                    	   UserSim[0][z] = UserSim[0][g];
                    	   UserSim[0][g] = temp;
                    	   float temp2 = UserSim[1][z];
                    	   UserSim[1][z] = UserSim[1][g];
                    	   UserSim[1][g] = temp2;
                    }
                }
            }
            
            for(int p = 1; p<rowcount; p++) {
                System.out.print(UserSim[1][p] + " ");
                }
             System.out.println();
            for(int p = 1; p<rowcount; p++) {
            		System.out.print(UserSim[0][p] + " ");
            }
            System.out.println("");
            
            String UserSim1[][] = null;
            UserSim1 = new String[2][rowcount];
            
            for(int l = 0; l<2; l++) {
            	for(int x = 0; x<rowcount; x++) {
            		UserSim1[l][x] = Float.toString(UserSim[l][x]);
            	}
            }

            return UserSim1;
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }        

	public static String[][] Itemreco(int UserNum) throws IOException{
		
		//DBConnect(args);

		Float array[][] = null;
		int rowcount = countLines("/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/yourtaste1/TasteData2.csv");
		
		array = new Float[rowcount][3];
				
		try {
			File csv = new File("/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/yourtaste1/TasteData2.csv");
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";
            int row =0 ,i;
            
            while ((line = br.readLine()) != null) {
                String[] token = line.split(",", -1);
                for(i=0;i<3;i++) {
                 array[row][i] = Float.parseFloat(token[i]);
                }
                
                for(i=0;i<3;i++) {
             //    System.out.print(array[row][i] + ",");
                }
            //   System.out.println("");
                
                row++;
            }
            br.close();

          //  System.out.print(array[2][0]);
    		float Usercount = array[rowcount-1][0];
    		int itemNum = 0;
    		
    		
    		float Usersimilar[][] = null;
    		String Usersimilar1[][] = null;
    		Usersimilar1 = Usersim(UserNum);
    		Usersimilar = new float[2][Usersimilar1[0].length];
    		for(int t = 0; t<2; t++) {
    			for(int q = 0; q<Usersimilar1[0].length; q++) {
    				Usersimilar[t][q] = Float.parseFloat(Usersimilar1[t][q]);
    			}
    		}     		System.out.println("");
    		
    		/*float Usergraph[][][] = null;
    		Usergraph = new float[(int) Usercount][5][2];*/
    		
    		for(int h = 0; h<rowcount; h++) {
    			if(array[h][0] == UserNum) {
    				itemNum++;
    			}
    		}
    		float targetUser[] = null;
    		targetUser = new float[itemNum];
    		
    		int q = 0;
    		for(int h = 0; h<rowcount; h++) {
    			if(array[h][0] == UserNum) {
    				targetUser[q] = array[h][1];
    				q++;
    			}
    		}
    		/*for(int c = 0;c<itemNum; c++ ) {
    		System.out.print(targetUser[c]);
    		}*/

    		float Itemrecom[][] = null;
    		Itemrecom = new float[2][5];
    		int index = 0;
    		float Userm = 0;
    		for(int h = 1; h<Usersimilar[1].length; h++) {
    			for(int o = 0; o<rowcount; o++) {
    				if(array[o][0] == Usersimilar[1][h] && Usersimilar[0][h] > 0.0) {
    						Itemrecom[0][index] = array[o][1];
    						Itemrecom[1][index] = array[o][2] + Itemrecom[1][index];
    						index++;
    				}
    			}
    			index = 0;
    			if(Usersimilar[0][h] > 0.0) {Userm++;}
    		}
    		
    		for(int h = 0; h<Itemrecom[1].length; h++) {
    			Itemrecom[1][h] = (float) ((Itemrecom[1][h])/Userm);
    		}
    	/*	
    		System.out.println();
    		for(int c = 0;c<5; c++ ) {
    		System.out.print(Itemrecom[0][c] + " ");
    		}
    		System.out.println();
    		for(int c= 0; c<5; c++) {
    			System.out.print(Itemrecom[1][c] + " ");
    		}
    	*/	
    		float Itemrecomarray[][] = null;
    		Itemrecomarray = new float[2][Itemrecom[1].length];

    		for(int h = 0; h<Itemrecom[1].length; h++) {
    			Itemrecomarray[0][h] = Itemrecom[0][h];
    			Itemrecomarray[1][h] = Itemrecom[1][h];
    		}
    		for(int h = 0; h<Itemrecom[1].length; h++) {
    			for(int k = 0; k<targetUser.length; k++) {
    				if(Itemrecom[0][h] == targetUser[k]) {
    					Itemrecomarray[1][h] = 0;
    				}
    			}
    		}
    		
    		  for (int g = 0; g < Itemrecomarray[1].length; g++) {
                  for (int z = g + 1; z < Itemrecomarray[1].length; z++) {
                      if (Itemrecomarray[1][g] < Itemrecomarray[1][z]) {
                      	   float temp = Itemrecomarray[0][z];
                      	 Itemrecomarray[0][z] = Itemrecomarray[0][g];
                      	Itemrecomarray[0][g] = temp;
                      	   float temp2 = Itemrecomarray[1][z];
                      	 Itemrecomarray[1][z] = Itemrecomarray[1][g];
                      	 Itemrecomarray[1][g] = temp2;
                      }
                  }
              }
  /*            
    		System.out.println();
    		for(int c = 0;c<5; c++ ) {
    		System.out.print(Itemrecomarray[0][c] + " ");
    		}
    		System.out.println();
    		for(int c= 0; c<5; c++) {
    			System.out.print(Itemrecomarray[1][c] + " ");
    		}
*/
    		
    		/*for(int a = 0; a<Usercount; a++) {
    			while(array[UserNum][0] == a+1) {
    				Usergraph[a][itemNum][0] = itemNum+1;
    				if(array[UserNum][2] > 3.5) {
    					Usergraph[a][itemNum][1] = 2;
    				}
    				else if (array[UserNum][2]<3.5 && array[UserNum][2]>0) {
    					Usergraph[a][itemNum][1] = 1;
    				}
    				else {
    					Usergraph[a][itemNum][1] = 0;
    				}
    				UserNum++;
    				itemNum++;
    			}
    		}*/
    		//System.out.println(Usergraph[0][0][0]);
    		
    		String UserResult[][];
    		String ItemResult[][];
    		
    		
    		UserResult = new String[2][Usersimilar[0].length];
    		ItemResult = new String[2][Itemrecomarray[0].length];
    		
    		for(int l = 0; l<2; l++) {
    			for(int f = 0; f<Usersimilar[0].length; f++) {
    				UserResult[l][f] = Float.toString(Usersimilar[l][f]);
    			}
    		}
    		
    		for(int l = 0; l<2; l++) {
    			for(int f = 0; f<Itemrecomarray[0].length; f++) {
    				ItemResult[l][f] = Float.toString(Itemrecomarray[l][f]);
    			}
    		}
    		
    		String UserResult1[];
    		UserResult1 = new String[UserResult[0].length];
    		String UserResult2[];
    		UserResult2 = new String[UserResult[1].length];
    		
    		String ItemResult1[];
    		ItemResult1 = new String[ItemResult[0].length];
    		String ItemResult2[];
    		ItemResult2 = new String[ItemResult[1].length];
    		
    		for(int l = 0; l<Usersimilar[0].length; l++) {
    			UserResult1[l] = UserResult[0][l];
    		}
    		for(int l = 0; l<Usersimilar[1].length; l++) {
    			UserResult2[l] = UserResult[1][l];
    		}
    		for(int l = 0; l<ItemResult[0].length; l++) {
    			ItemResult1[l] = ItemResult[0][l];
    		}
    		for(int l = 0; l<ItemResult[0].length; l++) {
    			ItemResult2[l] = ItemResult[1][l];
    		}
	
    		System.out.println();
    		for(int c = 0;c<5; c++ ) {
    		System.out.print(ItemResult[0][c] + " ");
    		}
    		System.out.println();
    		for(int c= 0; c<5; c++) {
    			System.out.print(ItemResult[1][c] + " ");
    		}
    		/*
    		System.out.println();
    		for(int c = 1;c<UserResult[0].length; c++ ) {
    		System.out.print(UserResult[0][c] + " ");
    		}
    		System.out.println();
    		for(int c= 1; c<UserResult[0].length; c++) {
    			System.out.print(UserResult[1][c] + " ");
    		}
*/

    		return ItemResult;

        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

}

