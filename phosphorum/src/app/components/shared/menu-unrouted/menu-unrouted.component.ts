import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-menu-unrouted',
  templateUrl: './menu-unrouted.component.html',
  styleUrls: ['./menu-unrouted.component.css'],
})
export class MenuUnroutedComponent implements OnInit {
  items: MenuItem[] | undefined;

  constructor() {
    this.items = [
      { label: 'Home', icon: 'pi pi-fw pi-home', routerLink: '/home' },
      {
        label: 'Users',
        icon: 'pi pi-fw pi-users',
        routerLink: '/admin/user/plist',
      },
      {
        label: 'Threads',
        icon: 'pi pi-fw pi-comments',
        routerLink: '/admin/thread/plist',
      },
      {
        label: 'Replies',
        icon: 'pi pi-fw pi-reply',
        routerLink: '/admin/reply/plist',
      },
    ];
  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
}
