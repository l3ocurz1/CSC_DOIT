import { Component, OnInit } from '@angular/core';
import { ProfileManagerService } from 'src/app/services/profile-manager.service';

@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.css']
})
export class NotificationListComponent implements OnInit {

  notifiche;

  constructor(private profileManager: ProfileManagerService) { }

  ngOnInit(): void {
    this.getNotifiche();
  }

  getNotifiche(): void {
    this.profileManager.getProfile().subscribe(data => {
      this.notifiche = data.notifiche;
    })
  }

  remove(id: number): void {
    this.profileManager.cancellaNotifica(id).subscribe(()=>
    this.getNotifiche())
  }

}
