import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GarageService } from './garage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [GarageService]
})
export class AppComponent implements OnInit {
  title = 'parfait';
  cars: any[] = [];

  constructor(private httpClient: HttpClient, private garageService: GarageService) {}

  ngOnInit(): void {
    console.log('on init....');
    this.garageService.getCars().subscribe((datas) => {
      this.cars = datas as unknown as any[];
    });
  }
}
