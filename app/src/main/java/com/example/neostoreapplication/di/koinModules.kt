package com.example.neostoreapplication.di

import com.example.neostoreapplication.Adapter.AddressAdapter
import com.example.neostoreapplication.Database.AddressDatabase
import com.example.neostoreapplication.AddressRepository
import com.example.neostoreapplication.ViewModel.AddressViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single { AddressDatabase.getInstance(context = get()

    )}
    factory { get<AddressDatabase>().noteDao() }
}

val repositoryModule = module {
    single { AddressRepository(get()) }
}

val uiModule = module {
    factory { AddressAdapter(androidContext()) }
    viewModel { AddressViewModel(get()) }
}