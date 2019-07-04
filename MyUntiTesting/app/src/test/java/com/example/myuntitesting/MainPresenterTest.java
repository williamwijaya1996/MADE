package com.example.myuntitesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Test
    public void volume() {
    }

    @Test
    public void calculateVolume() {
    }

    @Mock
    private MainPresenter presenter;
    private MainView view;

    @Before
    public void setUp() {
        view = mock(MainView.class);
        presenter = new MainPresenter(view);
    }

    @Test
    public void testVolumeWithIntegerInput() {
        double volume = presenter.volume(2, 8, 1);
        assertEquals(16, volume, 0.0001);
    }
}