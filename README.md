#  Laberinto EDL — Estructuras de Datos Lineales

## Descripción
Generador y resolvedor de laberintos aleatorios en Java. Encuentra el camino
de menor distancia desde la entrada (S) hasta la salida (E) usando
estructuras de datos implementadas desde cero.

## Equipo
| Nombre  | Fase   | Responsabilidad                                  |
|---------|--------|--------------------------------------------------|
| Manuel  | Fase 1 | Generador aleatorio, BFS, Cola, Lista Enlazada   |
| Juan    | Fase 2 | Tabla Hash, Pila/DFS, Árbol Binario              |
| Daniela | Fase 3 | Grafo, Recursive Backtracker, tamaño, README     |

## Estructuras de datos implementadas
| Clase | Propósito |
|-------|-----------|
| `Celda` | Fila, columna y tipo (W pared, C camino, S inicio, E salida) |
| `ListaEnlazada` | Guarda la secuencia del camino INICIO → SALIDA |
| `Cola` | Frontera del BFS, explora nivel por nivel |
| `Pila` | Frontera del DFS (exploración alternativa) |
| `TablaHash` | Caché de laberintos por semilla |
| `ArbolBinario` | Ranking de mejores soluciones (recorrido inOrden por pasos) |
| `Grafo` | Modelo formal: nodos = celdas caminables, aristas = 4 direcciones |

## Algoritmos de generación

### Aleatorio simple (Fase 1)
Cada celda tiene 30% de probabilidad de ser pared. Los bordes son paredes,
el inicio está en (1,1) y la salida en (N-2, N-2). Puede generar laberintos
sin solución.

### Recursive Backtracker (Fase 3)
Genera laberintos **perfectos**: existe exactamente un camino entre cualquier
par de celdas y no hay bucles. Por eso siempre hay camino de S a E.
Funciona como un DFS:
1. Se parte de (1,1), se marca como visitada y se abre como camino.
2. Se elige al azar un vecino no visitado a 2 celdas de distancia.
3. Se elimina la pared que hay entre la celda actual y ese vecino.
4. Se avanza al vecino y se repite. Si no hay vecinos sin visitar, se
   retrocede (backtracking).

Las dimensiones deben ser impares, así que un tamaño par se aumenta en 1
(por ejemplo, 10 pasa a 11×11).

## Tamaños disponibles
| Nombre | Tamaño |
|--------|--------|
| PEQUEÑO | 10×10 (queda en 11×11) |
| MEDIANO | 15×15 |
| GRANDE | 21×21 |

## Compilar y ejecutar
```bash
# Compilar todo
javac -encoding UTF-8 -d out src/fase1/*.java src/fase2/*.java src/fase3/*.java

# Fase 1: semilla por defecto
java -cp out fase1.Main

# Fase 2: BFS vs DFS + caché
java -cp out fase2.MainFase2

# Fase 3: semilla y tamaño configurables
java -cp out fase3.MainFase3 42 MEDIANO
java -cp out fase3.MainFase3 999 GRANDE
java -cp out fase3.MainFase3 7 PEQUENO
```

## BFS vs DFS
| Algoritmo | Garantiza el mínimo | Estructura | Complejidad |
|-----------|---------------------|------------|-------------|
| BFS | Sí | Cola | O(V+E) |
| DFS | No | Pila | O(V+E) |