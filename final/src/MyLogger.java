import java.util.Date;

public class MyLogger {
    private StringBuilder logMes;
    private Date date;
    MyLogger(){
        logMes = new StringBuilder();
        date = new Date();
    }
    public void writeLog(String str){
        if (str == null) return;
        logMes.append(new Date().toString().substring(11,19)).append(" ").append(str);
    }

    public StringBuilder getLogMes(){
      return logMes;
    }

    public void setLogMes(StringBuilder logMes) {this.logMes = logMes;}
}
