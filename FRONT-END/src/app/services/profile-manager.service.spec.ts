import { TestBed } from '@angular/core/testing';

import { ProfileManagerService } from './profile-manager.service';

describe('ProfileManagerService', () => {
  let service: ProfileManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfileManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
