
public class DayTime {
    private String timehhmm;
    private int timeInMins;

    public DayTime(CharSequence subSequence) {
        timehhmm = (String)subSequence;
    }

    public String getTimehhmm() {
        return timehhmm;
    }

    public boolean isValid() {
        if (timehhmm == null || timehhmm.length() != 4) {
            return false;
        }
        if (timehhmm.charAt(0) > '2' || timehhmm.charAt(0) < '0') {
            return false;
        } else if ((timehhmm.charAt(0) == '2' && timehhmm.charAt(1) > '3') 
                ||(timehhmm.charAt(0) < '2' && timehhmm.charAt(1) > '9')
                || timehhmm.charAt(1) < '0') {
            return false;
        } else if ((timehhmm.charAt(0) == '2' && timehhmm.charAt(1) > '3') 
                ||(timehhmm.charAt(0) < '2' && timehhmm.charAt(1) > '9')
                || timehhmm.charAt(1) < '0') {
            return false;
        } else if (timehhmm.charAt(2) < '0' || timehhmm.charAt(2) > '5' 
        || timehhmm.charAt(3) < '0' || timehhmm.charAt(3) > '9'){
            return false;
        } else {
            return true;
        }

    }

    @Override
    public String toString() {
        return "DayTime [timehhmm=" + timehhmm + "]";
    }

    public boolean isGreater(DayTime sdt) {
        if(!sdt.isValid()) throw new IllegalArgumentException("time invalid");
        return timeInMinsMethod(timehhmm) > timeInMinsMethod(sdt.getTimehhmm());
    }
    
    protected static int timeInMinsMethod(String sample) {
        int result;
        if(!new DayTime(sample).isValid()) throw new IllegalArgumentException("time invalid");

        result = sample.charAt(3) - '0' +
                (sample.charAt(2) - '0') * 10 +
                (sample.charAt(1) - '0') * 60 +
                (sample.charAt(0) - '0') * 10 * 60;

        return result;
    }
}