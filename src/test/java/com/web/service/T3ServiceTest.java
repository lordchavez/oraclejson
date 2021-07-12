package com.web.service;

import com.web.entity.T1;
import com.web.entity.T3;
import com.web.repository.T3Repository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class T3ServiceTest {

    @InjectMocks
    private T3ServiceImpl service;

    @Mock
    private T3Repository repository;

    @Test
    public void testFindById() {
        when( repository.findById( 1 ) ).thenReturn( new T3( 1, new T1(), "" ) );
        Assert.assertNotNull( service.findById( 1 ) );
    }
}
