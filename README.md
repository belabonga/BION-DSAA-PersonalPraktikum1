# 📘 Personal Assignment – Array vs ArrayList

## 📌 Deskripsi

Project ini dibuat untuk memenuhi **Personal Assignment – Week 5 (Sesi 10)** pada mata kuliah _Data Structures and Algorithm Analysis_.  
Tujuan utama dari praktikum ini adalah membandingkan **kinerja Array dan ArrayList** dalam operasi dasar berikut:

- Traversal
- Search (Linear & Binary)
- Insert
- Delete
- Sort

## 🗂️ Struktur Project

```
src/
├─ util/
│ ├─ Bench.java
│ └─ PrettyTable.java
├─ ArrayOperations.java
├─ ArrayListOperations.java
└─ Comparison.java
```

## ⚙️ Cara Menjalankan

1. Clone atau download project ini.
2. Pastikan sudah terinstal **Java 8+**.
3. Compile semua file ke folder `out/`:
   ```bash
   mkdir -p out
   javac -d out src/util/*.java src/*.java
   ```
4. Jalankan program:
   ```bash
   java -cp out Comparison
   ```

## 🖥️ Contoh Output

```bash
=== Perbandingan Kinerja (N=1000, repeat=5) ===
Operasi                 | Array (ms) | ArrayList (ms)
-----------------------------------------------------
Traversal               | 0.2009     | 0.5007
Search - Linear/indexOf | 0.0089     | 0.0150
Search - Binary (sorted)| 0.0010     | -
Insert (mid)/Add(end)   | 0.0051     | 0.0006
Delete First/remove     | 0.0134     | 0.0703
Sort                    | -          | 0.0561

Array Traversal: [10, 20, 30, 40, 50]
ArrayList Traversal: [10, 20, 30, 40, 50]

Pencarian 30 dalam Array: Ditemukan di indeks 2
Pencarian 30 dalam ArrayList: Ditemukan di indeks 2

Array setelah penyisipan elemen 25: [10, 20, 25, 30, 40, 50]
ArrayList setelah penyisipan elemen 25: [10, 20, 25, 30, 40, 50]

Waktu eksekusi pencarian pada Array: 0.0004 ms
Waktu eksekusi pencarian pada ArrayList: 0.0006 ms

```

## 📊 Analisis Singkat

- Traversal: Array lebih cepat karena tanpa overhead.

- Search: Linear search hampir sama; Binary search di Array jauh lebih cepat.

- Insert/Delete: Array lebih lambat di insert mid, ArrayList unggul untuk add di akhir.

- Sort: Kompleksitas O(n log n) pada keduanya, ArrayList sedikit lebih lambat.

## 👤 Author

Shania A. Mafaza (NIM : 2802638860) -
Computer Science - BINUS University
