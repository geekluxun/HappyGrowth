/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.geekluxun.www.happygrowth;


import android.content.Context;
import android.support.annotation.NonNull;

import com.geekluxun.www.happygrowth.data.FakeFoodRemoteDataSource;
import com.geekluxun.www.happygrowth.food.data.source.FoodRepository;
import com.geekluxun.www.happygrowth.food.data.source.local.FoodLocalDataSource;
import com.geekluxun.www.happygrowth.food.domain.usecase.AddFood;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of mock implementations for
 * {@link com.geekluxun.www.happygrowth.food.data.source.FoodDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static FoodRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        return FoodRepository.getInstance(FakeFoodRemoteDataSource.getInstance(),
                FoodLocalDataSource.getInstance(context));
    }

    public static AddFood provideAddFood(@NonNull Context context) {
        return  new AddFood(provideTasksRepository(context));
        //return new AddFood(provideTasksRepository(context), new com.example.android.architecture.blueprints.todoapp.tasks.domain.filter.FilterFactory());
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

//    public static GetTask provideGetTask(@NonNull Context context) {
//        return new GetTask(Injection.provideTasksRepository(context));
//    }
//
//    public static SaveTask provideSaveTask(@NonNull Context context) {
//        return new SaveTask(Injection.provideTasksRepository(context));
//    }
//
//    public static CompleteTask provideCompleteTasks(@NonNull Context context) {
//        return new CompleteTask(Injection.provideTasksRepository(context));
//    }
//
//    public static ActivateTask provideActivateTask(@NonNull Context context) {
//        return new ActivateTask(Injection.provideTasksRepository(context));
//    }
//
//    public static ClearCompleteTasks provideClearCompleteTasks(@NonNull Context context) {
//        return new ClearCompleteTasks(Injection.provideTasksRepository(context));
//    }
//
//    public static DeleteTask provideDeleteTask(@NonNull Context context) {
//        return new DeleteTask(Injection.provideTasksRepository(context));
//    }
//
//    public static GetStatistics provideGetStatistics(@NonNull Context context) {
//        return new GetStatistics(Injection.provideTasksRepository(context));
//    }
}
