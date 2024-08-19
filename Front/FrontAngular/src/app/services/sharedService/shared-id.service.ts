import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedIDService {
  private userId!: number;

  setUserId(id: number) {
    this.userId = id;
  }

  getUserId(): number {
    return this.userId;
  }


  constructor() { }
}
