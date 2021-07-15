package FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arguments {
    public static boolean switchend = false;
        public static void start() throws IOException{
            while(switchend != true){
                System.out.println("+---------------------------------------------------------------------------------------------+");
                System.out.println("|Unesite jedan od navedenih kommandi: LIST, INFO, CREATE_DIR, RENAME, COPY, MOVE, DELETE, END.|");
                System.out.println("+---------------------------------------------------------------------------------------------+");
                Scanner scan = new Scanner(System.in);
                String line = scan.nextLine(); 
                switch(line){
                case "LIST":
                    try{
                        System.out.println("Unesite putanje: ");
                        Scanner Listscan = new Scanner(System.in);
                        String Listsc = Listscan.nextLine();
                        File path = new File(Listsc);                                
                        if(path.exists() && path.isDirectory())
                        {
                            String[] strings = path.list();
                            System.out.println("+----------------------------------------+");
                            System.out.println("|\tIndex\tIme Foldera/Filea");   
                            System.out.println("+----------------------------------------+");                      
                            for(int i = 0; i < strings.length; i++){
                                System.out.println("|\t" + i + "\t" + strings[i]);
                            }
                            System.out.println("+----------------------------------------+");
                        }
                    }catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "INFO":
                    try{
                        System.out.println("Unesite putanje: ");
                        Scanner Infoscan = new Scanner(System.in);
                        String Infoscn = Infoscan.nextLine();
                        File Infopath = new File(Infoscn);                                
                        if(Infopath.exists() && Infopath.isDirectory()){
                            String leftAlignFormat = "| %-9s | %-35s | %-60s | %-9s | %-29s | %-29s | %n";
                            System.out.format("+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");                    
                            System.out.format(leftAlignFormat, "Index", "Ime", "Putanja", "Veličina", "Datum kreiranja", "Datum poslednje izmene");
                            System.out.format("+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n"); 
                            String[] stringsInfo = Infopath.list();
                            for(int i = 0; i < stringsInfo.length; i++)
                            {                                  
                                File file =new File(Infopath + "\\" + stringsInfo[i]);
                                Path path1 = Paths.get(Infopath + "\\" + stringsInfo[i]);
                                BasicFileAttributes attr;                               
                                attr = Files.readAttributes(path1, BasicFileAttributes.class);
                                System.out.format(leftAlignFormat, i , stringsInfo[i], Infopath + "\\" + stringsInfo[i] , file.length() + "KB", attr.creationTime(), attr.lastModifiedTime());                                                              
                            }
                                System.out.format("+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");                             
                        }
                    }catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                        
                    }
                    break;
                case "CREATE_DIR":
                    try{
                        System.out.println("+-------------------------------------------------------------+");
                        System.out.print("|Unesite putanje: ");                          
                        Scanner PutanjeCreate = new Scanner(System.in);
                        String Listcr = PutanjeCreate.nextLine();
                        File pathCreate = new File(Listcr);                                
                        if(pathCreate.exists() && pathCreate.isDirectory()){
                        System.out.println("+-------------------------------------------------------------+");
                        System.out.print("|Unesite ime koji želite da date folderu: ");
                        Scanner create = new Scanner(System.in);
                        String createline = create.nextLine();
                        File testDirectory = new File(pathCreate + "\\" + createline);
                        try {
                        if(!testDirectory.exists())
                        {
                        testDirectory.mkdir();
                        System.out.println("+-------------------------------------------------------------+");
                        System.out.println("|Folder je napravljen s imenom: " + testDirectory.getName());
                        System.out.println("+-------------------------------------------------------------+\n");
                        }
                        else
                        {
                        System.out.println("+-------------------------------------------------------------+");
                        System.out.println("Folder s imenom već postoji: " + testDirectory.getName() +"\n");
                        System.out.println("+-------------------------------------------------------------+\n");
                        }

                        } catch (Exception e) {
                        System.out.println("+-------------------------------------------------------------+");
                        System.out.println("Došlo do greške dok, smo probali da napravimo ovaj folder:  "
                                            + testDirectory.getName());
                        System.out.println("+-------------------------------------------------------------+\n");
                                } 
                    }
                    }catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                        
                    }
                    break;
                case "RENAME":
                    try{
                        System.out.println("+-------------------------------------------------------------+");
                        System.out.print("|1rnd putanje kojim želite da izemnite ime: ");
                        Scanner PutanjeRen = new Scanner(System.in);
                        String rename = PutanjeRen.nextLine();
                        File fileren = new File(rename);

                        System.out.println("+-------------------------------------------------------------+");
                        System.out.print("|Putanje do istog foldera/file-a + ime koji želite dati folderu: ");
                        Scanner Novoime = new Scanner(System.in);
                        String newname = Novoime.nextLine();
                        File Newname = new File(newname); 

                        if(fileren.renameTo 
                           (Newname)) 
                        {                                 
                            System.out.println("+-------------------------------------------------------------+");
                            System.out.println("|Uspešno ste izmenili ime!"); 
                            System.out.println("+-------------------------------------------------------------+\n");
                        } 
                        else
                        { 
                            System.out.println("+-------------------------------------------------------------+");
                            System.out.println("|Niste uspeli da izmenite ime!"); 
                            System.out.println("+-------------------------------------------------------------+\n");
                        }
                    }catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                        
                    }
                    break;
                case "COPY":
                    try{
                        System.out.println("Unesite 1rnd putanje: ");
                        Scanner FirstPath = new Scanner(System.in);
                        String firstpth = FirstPath.nextLine();
                        File firstPath = new File(firstpth);    
                        
                        
                        String name = new File(firstpth).getName();
                        
                        System.out.println("Unesite 2rnd putanje: ");
                        Scanner SecPath = new Scanner(System.in);
                        String secpth = SecPath.nextLine();
                        File secPath = new File(secpth + "\\" +name); 
                        
                        
                       if(!secPath.exists()){
                        copyFolder(firstPath, secPath);
                       }else{
                           System.out.println("+-------------------------------------------------------------+");
                           System.out.println("|Folder već postoji na datoj lokaciji!");
                           System.out.println("+-------------------------------------------------------------+\n");
                       }    
                    }catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "MOVE":
                    try{
                        System.out.println("Unesite 1rnd putanje: ");
                        Scanner FirstPathMove = new Scanner(System.in);
                        String firstpthmo = FirstPathMove.nextLine();                    
                        File firstPathmove = new File(firstpthmo);
                                                             
                        String namemove = new File(firstpthmo).getName();
                        
                        
                        System.out.println("Unesite 2rnd putanje: ");
                        Scanner SecPathMove = new Scanner(System.in);
                        String secpthmo = SecPathMove.nextLine();
                        File secPathmove = new File(secpthmo + "\\" + namemove); 
                        
                        
                       if(!secPathmove.exists()){
                        moveFolder(firstPathmove, secPathmove);                       
                       }else{
                           System.out.println("+-------------------------------------------------------------+");
                           System.out.println("|Folder već postoji na datoj lokaciji!");
                           System.out.println("+-------------------------------------------------------------+\n");
                       }              
                    }catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "DELETE":
                        try{
                        System.out.println("+-------------------------------------------------------------+");
                        System.out.print("Unesite putanje do foldera koji želite da izbrišete: ");
                        Scanner PutanjeDelete = new Scanner(System.in);
                        String Listdle = PutanjeDelete.nextLine();
                        File filedel = new File(Listdle);
                        if(filedel.exists())
                        {
                        filedel.delete();
                        System.out.println("+-------------------------------------------------------------+");
                        System.out.println("|Uspešno ste izbrisali folder s imenom: " + filedel.getName() + "");
                        System.out.println("+-------------------------------------------------------------+\n");
                        } 
                        else
                        {
                        System.out.println("+-----------------------------------------------------------------------+");
                        System.out.println("|Folder:  " + filedel.getName() + " nije se moglo izbrisati jer ne postoji!");
                        System.out.println("+-----------------------------------------------------------------------+\n");
                        }
                        }catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "END":
                    switchend = true;
                    break;
                default:
                    System.out.println("+-------------------------------------------------+");
                    System.out.println("|Ne postoji komanda! Koristite jedan od navedenih!|");
                    System.out.println("+-------------------------------------------------+\n");
                }
        }
    }
        private static void copyFolder(File sourceFolder, File destinationFolder) throws IOException
    {
        if (sourceFolder.isDirectory())
        {
            if (!destinationFolder.exists())
            {
                destinationFolder.mkdir();
            }
             
            String files[] = sourceFolder.list();
             
            for (String file : files)
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
                 
                copyFolder(srcFile, destFile);
            }
        }
        else
        {
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Folder je uspešno kopiran!");
        }
    }
        
        private static void moveFolder(File sourceFoldermove, File movedestinationFolder) throws IOException
    {   
        if (sourceFoldermove.isDirectory())
        {
            if (!movedestinationFolder.exists())
            {
                movedestinationFolder.mkdir();
            }
             
            String files[] = sourceFoldermove.list();          
             
            for (String file : files)
            {
                File srcFile = new File(sourceFoldermove, file);
                File destFile = new File(movedestinationFolder, file);
                 
                moveFolder(srcFile, destFile);               
            }
            sourceFoldermove.delete();
        }
        else
        {
            Files.move(sourceFoldermove.toPath(), movedestinationFolder.toPath());
            System.out.println("Folder je uspešno premešten!");
        }
    }
        
}
