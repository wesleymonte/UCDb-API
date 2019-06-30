package com.ufcg.psoft.ucdb.core.models.comparators;

import com.ufcg.psoft.ucdb.core.models.Subject;

import java.util.Comparator;

public class CommentsCompare implements Comparator<Subject> {

    @Override
    public int compare(Subject s1, Subject s2) {
        Integer size1 = s1.getCommentList().size();
        Integer size2 = s2.getCommentList().size();
        if(size1 < size2) return -1;
        if(size1 > size2) return 1;
        else return 0;
    }
}
