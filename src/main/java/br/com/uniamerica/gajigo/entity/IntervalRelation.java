package br.com.uniamerica.gajigo.entity;

public enum IntervalRelation {
    // Notation
    // A > B |= A completely after B
    // A < B |= A completely before B
    // A = B |= A equals B
    After,                   // a.start > b.end
    StartTouching,           // a.start = b.end
    StartInside,             // a.start < b.end
    InsideStartTouching,     // a.start = b.start and a.end < b.end
    EnclosingStartTouching,  // a.start = b.start and a.end > b.end
    Enclosing,               // a.start < b.start and a.end > b.end
    EnclosingEndTouching,    // a.start < b.start and a.end = b.end
    ExactMatch,              // a.start = b.start and a.end = b.end
    Inside,                  // a.start > b.start and a.end < b.end
    InsideEndTouching,       // a.start > b.start and a.end = b.end
    EndInside,               // a.start < b.start and a.end < b.end
    EndTouching,             // a.end = b.start
    Before,                  // a.end < b.start
}
