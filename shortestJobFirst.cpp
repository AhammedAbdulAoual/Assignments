//Operating System
//Shortest Job First Algorithm
//Implementation using Cpp

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Proc {
    int id;
    int at;
    int bt;
    int ct;
    int wt;
    int tt;
};

bool compArrival(const Proc &a, const Proc &b) {
    return a.at < b.at;
}

void calcCompletion(vector<Proc> &procs) {
    int currTime = 0;
    for (int i = 0; i < procs.size(); ++i) {
        if (currTime < procs[i].at) {
            currTime = procs[i].at;
        }
        procs[i].ct = currTime + procs[i].bt;
        procs[i].tt = procs[i].ct - procs[i].at;
        procs[i].wt = procs[i].tt - procs[i].bt;
        currTime = procs[i].ct;
    }
}

int main() {
    int n;
    cout << "Enter the number of processes: ";
    cin >> n;

    vector<Proc> procs(n);
    for (int i = 0; i < n; ++i) {
        procs[i].id = i + 1;
        cout << "Enter arrival time for process " << procs[i].id << ": ";
        cin >> procs[i].at;
        cout << "Enter burst time for process " << procs[i].id << ": ";
        cin >> procs[i].bt;
    }

    sort(procs.begin(), procs.end(), compArrival);

    calcCompletion(procs);

    cout << "Proc ID\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time\n";
    for (int i = 0; i < n; ++i) {
        cout << procs[i].id << "\t\t" << procs[i].at << "\t\t" << procs[i].bt << "\t\t"
             << procs[i].ct << "\t\t" << procs[i].wt << "\t\t" << procs[i].tt << "\n";
    }

    return 0;
}
