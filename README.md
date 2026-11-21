# Laberinto EDL — Fase 1

Generador y solucionador de laberintos usando BFS (Breadth-First Search) con estructuras de datos propias.

## Estructuras de datos implementadas

| Clase | Propósito |
|-------|-----------|
| `Celda` | Nodo básico: fila, columna, tipo (PARED/CAMINO/INICIO/SALIDA) |
| `ListaEnlazada` | Almacena la secuencia de coordenadas del camino solución |
| `Cola` | Frontera BFS para explorar nivel por nivel |
| `Laberinto` | Matriz `char[][]`, generación aleatoria, impresión ASCII y BFS |
| `Main` | Punto de entrada: recibe semilla, ejecuta y muestra resultados |

## Compilar y ejecutar

```bash
# Compilar
javac -d out src/fase1/*.java

# Ejecutar con semilla por defecto (42)
java -cp out fase1.Main

# Ejecutar con semilla personalizada
java -cp out fase1.Main 12345
```

## Autor

Manuel — Fase 1
