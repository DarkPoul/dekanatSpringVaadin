package com.dekanat.ntu.dekanat.listener;

import com.dekanat.ntu.dekanat.entity.PlansEntity;

import java.util.List;

public interface SavePlanListener {
    void onSave(PlansEntity plansEntity, List<String> students);
}
