import { TestBed } from '@angular/core/testing';

import { CompetenceManagerService } from './competence-manager.service';

describe('CompetenceManagerService', () => {
  let service: CompetenceManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompetenceManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
