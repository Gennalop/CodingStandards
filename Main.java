
import java.util.*;

class student {
    String id;
    String name;
    List<Number> gradez = new ArrayList<>();
    String pass = "unknown";
    boolean honor;
    double avg;
    String letterGrade;

    public student(String i, String n) {
        this.id = validarCampo("ID", i);
        this.name = validarCampo("Nombre", n);
        this.gradez = new ArrayList<>();
    }

    private String validarCampo(String nombreCampo, String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            System.out.println(nombreCampo + " inválido. Se asignará 'N/A'.");
            return "N/A";
        }
        return valor;
    }

    public void AddG(Object g) {
        if (g instanceof Number) {
            double val = ((Number) g).doubleValue();
            if (val >= 0 && val <= 100) {
                gradez.add((Number) g);
            } else {
                System.out.println("Valor no válido (fuera de rango 0–100): " + val);
            }
        } else {
            System.out.println("Valor no válido (no es número): " + g);
        }
    }

    public void average() {
        double total = 0;
        for (Object g : gradez) {
            total += ((Number) g).doubleValue(); // ClassCastException
        }
        if (gradez.isEmpty()) {
            avg = 0;
            letterGrade = "N/A";
            return;
        }
        avg = total / gradez.size();
        setLetterGrade(avg);
        setPass(avg);
    }

    private void setLetterGrade(double avg) {
        if (avg >= 90) {
            letterGrade = "A";
        } else if (avg >= 80) {
            letterGrade = "B";
        } else if (avg >= 70) {
            letterGrade = "C";
        } else if (avg >= 60) {
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }
    }

    private void setPass(double avg) {
        if (avg >= 60) {
            pass = "Passed";
        } else {
            pass = "Failed";
        }
    }

    public void checkHonorStatus() {
        if (avg >= 90) {
            honor = true; // yes = true
        }
    }

    public void removeGrade(int i) {
        if (i >= 0 && i < gradez.size()) {
            gradez.remove(i);
        } else {
            System.out.println("Índice inválido: " + i + ". No se puede eliminar.");
        }
    }

    public void reportCard() {
        System.out.println("Student: " + name);
        System.out.println("ID: " + id);
        System.out.println("Grades #: " + gradez.size());
        System.out.println("Average: " + letterGrade + " = " + avg);
        System.out.println("Honor Roll: " + honor);
        System.out.println("Pass: " + pass);
    }
}

public class Main {
    public static void main(String[] args) {
        student s = new student("abc", null);
        s.AddG(59);
        s.AddG("Ninety");
        s.average();
        s.checkHonorStatus();
        s.removeGrade(9);
        s.reportCard();
    }
}
