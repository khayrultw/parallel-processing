#include <bits/stdc++.h>
#include <mpi.h>

using namespace std;

int main(int argv, char *argc[])
{
    int rank, size;

    MPI_Init(&argv, &argc);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    cout << rank << endl;

    MPI_Finalize();
}