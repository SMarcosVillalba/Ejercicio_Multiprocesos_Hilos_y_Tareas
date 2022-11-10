import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {
    private Queue<String> colaExamenes;

    public BufferExamenes() {
        colaExamenes = new LinkedList<String>();
    }

    public synchronized void fabricarNuevoExamen(String codigo) {

        // Añade el código pasado como argumento a la cola  y libera el hilo que está intentando consumir  un nuevo examen.

        colaExamenes.add(codigo);

        notify();
    }


    public synchronized String consumirExamen() {
        int esperas = 0;

        while (colaExamenes.isEmpty() && esperas<30){
            esperas++;
            try {
                wait(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }if (esperas<30) {
            String tri = colaExamenes.remove();
            return tri;
        } else {
            return null;
        }

    }
}
