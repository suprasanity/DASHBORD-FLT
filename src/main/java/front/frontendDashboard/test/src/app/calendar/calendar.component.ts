import {Component, OnInit} from '@angular/core';
import {AuthServiceService} from "../auth-service.service";
import {Tache} from "../tache";

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  days: Date[] = [];
  hours: number[] = [];
  tache : Tache[]= [];

  constructor(private authService : AuthServiceService) {
    // Populate the days array with the next 7 days
    for (let i = 0; i < 7; i++) {
      const date = new Date();
      date.setDate(date.getDate() + i);
      this.days.push(date);
    }

    // Populate the hours array with the hours of the day
    for (let i = 0; i < 24; i++) {
      this.hours.push(i);
    }
  }

  // Get the text of the note for the given hour and day
  getNoteText(hour: number, day: Date): string {
    const note = localStorage.getItem(`${day.toISOString()}-${hour}`);
    return note ? note : '';
  }

  // Add a note for the given hour and day
  addNote(hour: number, day: Date): void {
    const note = prompt('Enter a note:');
    if (note) {
      localStorage.setItem(`${day.toISOString()}-${hour}`, note);
    }
  }

  ngOnInit(): void {
    const data = {
      id: localStorage.getItem('USER')
    }
    this.authService.getAllTache(data).subscribe((result)=>{
      this.tache = result;
    })
    console.log(this.tache[0].nom)
    alert(this.tache[0].nom)
}
}
